package com.rk.batbudget.userservice.controller;

import com.rk.batbudget.userservice.entity.User;
import com.rk.batbudget.userservice.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable long id) {
        return userRepository.findById(id);
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
