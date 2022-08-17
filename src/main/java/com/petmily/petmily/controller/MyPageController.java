package com.petmily.petmily.controller;

import com.petmily.petmily.dto.MyPageDto;
import com.petmily.petmily.model.MyPage;
import com.petmily.petmily.model.User;
import com.petmily.petmily.service.MyPageService;
import com.petmily.petmily.util.Result;
import com.petmily.petmily.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mypage")
public class MyPageController {
    private final MyPageService mypageService;

    @Autowired
    public MyPageController(MyPageService mypageService) { this.mypageService = mypageService; }

    @GetMapping
    public ResponseEntity<Result<MyPageDto>> retrieve(@AuthenticationPrincipal User user){
        MyPage myPage = mypageService.retrieveMypage(user);
        return Result.toResult(ResultCode.CONTENT_RETRIEVE_SUCCESS, MyPageDto.builder().id(myPage.getId()).build());
    }

    @PostMapping("/create")
    public ResponseEntity<Result<MyPageDto>> create(@RequestBody MyPageDto myPageDto,
                                                    @AuthenticationPrincipal User user) {
        MyPage myPage = mypageService.createMypage(user, myPageDto);
        return Result.toResult(ResultCode.CONTENT_CREATE_SUCCESS, MyPageDto.builder().id(myPage.getId()).build());

    }


}
