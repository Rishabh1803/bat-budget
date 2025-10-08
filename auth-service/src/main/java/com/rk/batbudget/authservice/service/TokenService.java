package com.rk.batbudget.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TokenService {

    public static final String REFRESH_TOKEN = "refreshToken:";

    public void storeRefreshToken(String email, String refreshToken) {
//        stringRedisTemplate.opsForValue()
//                .set(REFRESH_TOKEN + email, refreshToken, Duration.ofDays(7));
    }

    public boolean validateRefreshToken(String email, String refreshToken) {
//        return refreshToken.equals(stringRedisTemplate.opsForValue().get(REFRESH_TOKEN + email));
        return false;
    }

    public void invalidateRefreshToken(String email) {
//        stringRedisTemplate.delete(REFRESH_TOKEN + email);
    }
}
