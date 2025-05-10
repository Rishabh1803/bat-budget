package com.rk.batbudget.userservice.service;

import com.rk.batbudget.common.entity.User;
import com.rk.batbudget.userservice.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @CachePut(value = "userCache", key = "#user.id")
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Cacheable(value = "userCache", key = "#id")
    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @CacheEvict(value = "userCache", key = "#id")
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }
}
