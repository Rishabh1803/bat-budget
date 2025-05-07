package com.rk.batbudget.authservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // sets the main user identity
                .setIssuedAt(new Date()) // when the token was issued
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1-day validity
                .signWith(SignatureAlgorithm.HS256, secret) // signed with our secret
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
