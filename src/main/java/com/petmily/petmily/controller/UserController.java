package com.petmily.petmily.controller;

import com.petmily.petmily.dto.SignupReq;
import com.petmily.petmily.service.UserService;
import com.petmily.petmily.util.Result;
import com.petmily.petmily.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Result> registerUser(@RequestBody SignupReq requestDto){
        userService.registerUser(requestDto);
        return Result.toResult(ResultCode.SIGNUP_SUCCESS);
    }

    @GetMapping("/test")
    public String getMappingTest(@RequestParam String id, @RequestParam String name) {
        return "ID : " + id + ", NAME : " + name;
    }



}

