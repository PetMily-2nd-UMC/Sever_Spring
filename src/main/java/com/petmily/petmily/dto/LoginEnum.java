package com.petmily.petmily.dto;

import lombok.Getter;
import org.springframework.http.HttpMethod;

@Getter
public enum LoginEnum {

    General(null, null),
    Naver("https://nid.naver.com/oauth2.0/token", "https://openapi.naver.com/v1/nid/me"),
    Kakao("https://kauth.kakao.com/oauth/token", "https://kapi.kakao.com/v2/user/me"),
    Google("https://oauth2.googleapis.com/token", "https://www.googleapis.com/oauth2/v1/userinfo");

    private String tokenUrl;
    private String infoUrl;

    LoginEnum(String tokenUrl, String infoUrl) {
        this.tokenUrl = tokenUrl;
        this.infoUrl = infoUrl;
    }
}
