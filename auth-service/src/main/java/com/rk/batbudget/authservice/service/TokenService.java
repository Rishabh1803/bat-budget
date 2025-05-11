package com.rk.batbudget.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final StringRedisTemplate stringRedisTemplate;

    public void storeRefreshToken(String email, String refreshToken) {
        stringRedisTemplate.opsForValue()
                .set("refreshToken:" + email, refreshToken, Duration.ofDays(7));
    }

    public boolean validateRefreshToken(String email, String refreshToken) {
        return refreshToken.equals(stringRedisTemplate.opsForValue().get("refreshToken:" + email));
    }

    public void invalidateRefreshToken(String email) {
        stringRedisTemplate.delete("refreshToken:" + email);
    }
}
