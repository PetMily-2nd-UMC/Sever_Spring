package com.petmily.petmily.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupReq {
    private String username;
    private String password;
    private String nickname;
    private String imgUrl;
    private boolean admin = false;
    private String adminToken = "";
}
