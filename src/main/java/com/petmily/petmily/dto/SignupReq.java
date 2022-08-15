package com.petmily.petmily.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class SignupReq {
    private String email;
    private String password;
    private String nickname;
    private MultipartFile image = null;
    private boolean admin = false;
    private String adminToken = "";
}
