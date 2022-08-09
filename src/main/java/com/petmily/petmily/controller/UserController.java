package com.petmily.petmily.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.petmily.petmily.dto.LoginReq;
import com.petmily.petmily.dto.SignupReq;
import com.petmily.petmily.dto.TokenDto;
import com.petmily.petmily.service.GoogleUserService;
import com.petmily.petmily.service.KakaoUserService;
import com.petmily.petmily.service.NaverUserService;
import com.petmily.petmily.service.UserService;
import com.petmily.petmily.util.Result;
import com.petmily.petmily.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    private final KakaoUserService kakaoUserService;

    private final NaverUserService naverUserService;

    private final GoogleUserService googleUserService;

    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService, NaverUserService naverUserService, GoogleUserService googleUserService) {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
        this.naverUserService = naverUserService;
        this.googleUserService = googleUserService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Result> registerUser(@RequestBody SignupReq requestDto){
        userService.registerUser(requestDto);
        return Result.toResult(ResultCode.SIGNUP_SUCCESS);
    }

    @PostMapping("/login")
    public ResponseEntity<Result<TokenDto>> loginUser(@RequestBody LoginReq requestDto){
        TokenDto tokenDto = userService.loginUser(requestDto);
        return Result.toResult(ResultCode.LOGIN_SUCCESS, tokenDto);
    }

    @GetMapping("/kakao")
    public ResponseEntity<Result<TokenDto>> kakaoLoginUser(@RequestParam String code) throws JsonProcessingException {
        TokenDto tokenDto = kakaoUserService.loginUser(code);
        return Result.toResult(ResultCode.LOGIN_SUCCESS, tokenDto);
    }

    @GetMapping("/naver")
    public ResponseEntity<Result<TokenDto>> naverLoginUser(@RequestParam(value = "code") String code, @RequestParam(value = "state") String state) throws JsonProcessingException {
        TokenDto tokenDto = naverUserService.loginUser(code, state);
        return Result.toResult(ResultCode.LOGIN_SUCCESS, tokenDto);
    }


    @GetMapping("/google")
    public ResponseEntity<Result<TokenDto>> getGoogleAccessToken(@RequestParam String code) throws JsonProcessingException {
        TokenDto tokenDto = googleUserService.loginUser(code);
        return Result.toResult(ResultCode.LOGIN_SUCCESS, tokenDto);
    }

    @GetMapping("/do")
    public String test() {
        return "login";
    }

    /*
    @GetMapping("/test")
    public String getMappingTest(@RequestParam String id, @RequestParam String name) {
        return "ID : " + id + ", NAME : " + name;
    }
    */


}

