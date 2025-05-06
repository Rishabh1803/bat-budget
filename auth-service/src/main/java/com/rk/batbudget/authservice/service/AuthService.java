package com.rk.batbudget.authservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rk.batbudget.authservice.dto.LoginRequest;
import com.rk.batbudget.authservice.dto.LoginResponse;
import com.rk.batbudget.authservice.dto.RegisterRequest;
import com.rk.batbudget.authservice.dto.UserRegisteredEvent;
import com.rk.batbudget.authservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

//    In-memory DB
    private final Map<String, RegisterRequest> userStore = new ConcurrentHashMap<>();

    public void register(RegisterRequest registerRequest) {
        if (userStore.containsKey(registerRequest.getEmail())) {
            throw new IllegalArgumentException("User already exists");
        }
        String hashedPassword = passwordEncoder.encode(registerRequest.getPassword());

        RegisterRequest user = new RegisterRequest(registerRequest.getName(), registerRequest.getEmail(), hashedPassword);
        userStore.put(user.getEmail(), user);

        UserRegisteredEvent event = new UserRegisteredEvent(registerRequest.getEmail(), registerRequest.getName());
        try {
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("user-registered", json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize event", e);
        }
    }

    public List<RegisterRequest> getUser(String email) {
        return List.of(userStore.get(email));
    }

    public LoginResponse login(LoginRequest loginRequest) {
        RegisterRequest user = userStore.get(loginRequest.getEmail());
        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new LoginResponse(token);
    }
}
