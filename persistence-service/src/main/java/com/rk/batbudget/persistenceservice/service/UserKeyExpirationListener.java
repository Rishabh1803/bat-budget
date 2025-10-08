package com.rk.batbudget.persistenceservice.service;

import com.rk.batbudget.persistenceservice.dao.UserRepository;
import com.rk.batbudget.persistenceservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserKeyExpirationListener {

    @Autowired
    private UserRepository userRepository;

//    @Override
    public void onMessage(
//            Message message,
            byte[] pattern) {
//        String expiredKey = message.toString();
//        Object userObj = redisTemplate.opsForValue().get(expiredKey);
//        if (userObj instanceof User user) {
//            userRepository.save(user);
//        }
    }
}
