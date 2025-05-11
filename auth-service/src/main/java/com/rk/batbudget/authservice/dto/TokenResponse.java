package com.rk.batbudget.authservice.dto;

import lombok.Data;

@Data
public class TokenResponse {

    private final String accessToken;
    private final String refreshToken;
}
