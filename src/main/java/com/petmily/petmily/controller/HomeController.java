package com.petmily.petmily.controller;

import com.petmily.petmily.dto.ContentDto;
import com.petmily.petmily.dto.TopPostDto;
import com.petmily.petmily.dto.commPost.CommPostReq;
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
    public ResponseEntity<Result<TopPostDto>> getTopPost(){
        //Content DB로부터 top5 컨텐츠를 가져옴
        List<ContentDto> contents = homeService.getTopContent();
        List<CommPostReq> commPosts = homeService.getTopCommPost();

        return Result.toResult(ResultCode.LOAD_SUCCESS, TopPostDto.builder()
                .contents(contents)
                        .commPosts(commPosts)
                .build());
    }


}
