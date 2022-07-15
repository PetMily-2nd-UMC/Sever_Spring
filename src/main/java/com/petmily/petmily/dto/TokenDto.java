package com.petmily.petmily.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TokenDto {
    private String grantType = "Bearer ";
    private final String accessToken;
    private final String refreshToken;
    private final Long accessTokenExpireIn;
}
