package com.rk.batbudget.cacheservice;

import com.rk.batbudget.cacheservice.service.CacheService;
import com.rk.batbudget.common.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

class CacheServiceApplicationTests {

//    @Autowired
//    private CacheService cacheService;
//
//    @BeforeEach
//    void setup() {
//        String cacheName = "userCache";
//        long key = 123;
//        User user = new User(123, "Alice", "alice@example.com");
//        cacheService.put(cacheName, key, user);
//    }
//
//    @Test
//    void testCacheOperations() {
//        cacheService.clear("userCache");
//        Assertions.assertTrue(cacheService.get("userCache", "Alice", User.class).isEmpty());
//    }
//
//    @Test
//    void testGetOperation() {
//        Optional<User> cachedUser = cacheService.get("userCache", 123, User.class);
//        Assertions.assertTrue(cachedUser.isPresent());
//        Assertions.assertEquals("Alice", cachedUser.get().getName());
//    }
//
//    @Test
//    void testEvictOperation() {
//        cacheService.evict("userCache", 123);
//        Optional<User> evictedUser = cacheService.get("userCache", 123, User.class);
//        Assertions.assertTrue(evictedUser.isPresent());
//    }

}
