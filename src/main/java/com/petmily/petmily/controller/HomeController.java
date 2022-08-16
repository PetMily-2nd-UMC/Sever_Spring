package com.petmily.petmily.controller;

import com.petmily.petmily.dto.ContentDto;
import com.petmily.petmily.dto.ProfileDto;
import com.petmily.petmily.dto.TopPostDto;
import com.petmily.petmily.dto.commPost.CommPostReq;
import com.petmily.petmily.model.User;
import com.petmily.petmily.service.HomeService;
import com.petmily.petmily.service.UserService;
import com.petmily.petmily.util.Result;
import com.petmily.petmily.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final HomeService homeService;

    private final UserService userService;

    @Autowired
    public HomeController(HomeService homeService, UserService userService) {
        this.homeService = homeService;
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<Result<TopPostDto>> getTopPost(){
        //Content DB로부터 top5 컨텐츠를 가져옴
        List<ContentDto> contents = homeService.getTopContent();
        List<CommPostReq> commPosts = homeService.getTopCommPost();

        return Result.toResult(ResultCode.LOAD_SUCCESS, TopPostDto.builder()
                .contents(contents)
                        .commPosts(commPosts)
                .build());
    }

    @GetMapping("/profile")
    public ResponseEntity<Result<ProfileDto>> getMyProfile(@AuthenticationPrincipal User user){
        return Result.toResult(ResultCode.LOAD_SUCCESS, new ProfileDto().makeResponse(user));
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<Result<ProfileDto>> getUserProfile(@PathVariable Long userId){
        User user = userService.getUserProfile(userId);
        return Result.toResult(ResultCode.LOAD_SUCCESS, new ProfileDto().makeResponse(user));
    }

}
