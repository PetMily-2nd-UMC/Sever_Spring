package com.petmily.petmily.controller;

import com.petmily.petmily.dto.ContentDto;
import com.petmily.petmily.dto.TopPostDto;
import com.petmily.petmily.service.HomeService;
import com.petmily.petmily.util.Result;
import com.petmily.petmily.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }


    @PostMapping
    public ResponseEntity<Result<TopPostDto>> registerUser(){
        List<ContentDto> contents = homeService.getTopContent();
        List<ContentDto> tmps = null; //초니님 코드에 맞게 코드 수정해주세요.
        return Result.toResult(ResultCode.LOAD_SUCCESS, TopPostDto.builder()
                                                .contents(contents).tmps(tmps).build());
    }

}
