package com.petmily.petmily.controller;

import com.petmily.petmily.dto.MyLogDto;
import com.petmily.petmily.dto.MyPageDto;
import com.petmily.petmily.dto.TopPostDto;
import com.petmily.petmily.model.MyLog;
import com.petmily.petmily.model.MyPage;
import com.petmily.petmily.model.User;
import com.petmily.petmily.service.MyLogService;
import com.petmily.petmily.service.MyPageService;
import com.petmily.petmily.util.Result;
import com.petmily.petmily.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mylog")
public class MyLogController {
    private final MyLogService myLogService;

    @Autowired
    public MyLogController(MyLogService myLogService) { this.myLogService = myLogService; }

    @GetMapping
    public ResponseEntity<Result<TopPostDto>> retrieveAllLog(@AuthenticationPrincipal User user){
//    public ResponseEntity<Result<TopPostDto>> retrieveAllLog(){
        List<MyLogDto> myLogs = myLogService.retrieveAllLog(user);
//        List<MyLog> myLogs = myLogService.retrieveAllLog();
        return Result.toResult(ResultCode.CONTENT_RETRIEVE_SUCCESS, TopPostDto.builder().myLogs(myLogs).build());
    }

    @PostMapping("/create")
    public ResponseEntity<Result<MyLogDto>> create(@RequestBody MyLogDto myLogDto,
                                                    @AuthenticationPrincipal User user) {
        MyLog myLog = myLogService.createMyLog(user, myLogDto);
        return Result.toResult(ResultCode.CONTENT_CREATE_SUCCESS, MyLogDto.builder().id(myLog.getId()).build());

    }


}
