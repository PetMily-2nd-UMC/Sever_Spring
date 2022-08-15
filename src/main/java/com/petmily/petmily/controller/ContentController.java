package com.petmily.petmily.controller;

import com.petmily.petmily.dto.ContentDto;
import com.petmily.petmily.dto.ContentReq;
import com.petmily.petmily.model.Content;
import com.petmily.petmily.model.User;
import com.petmily.petmily.service.ContentService;
import com.petmily.petmily.util.Result;
import com.petmily.petmily.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {
    private final ContentService contentService;
    private static final Logger logger = LoggerFactory.getLogger(ContentController.class);

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    //정보 게시물 작성
    @PostMapping("/write/{categoryId}")
    public ResponseEntity<Result<ContentDto>> createContent(@PathVariable Long categoryId,
                                                @ModelAttribute ContentReq requestDto,
                                                @AuthenticationPrincipal User user){
        //logger.error("createContent");
        Content content = contentService.createContent(categoryId, user, requestDto);

        return Result.toResult(ResultCode.PUBLISH_SUCCESS, ContentDto.builder()
                                                        .id(content.getId()).build());
    }

    //정보 게시물 수정하기
    @PostMapping("/modify/{contentId}")
    public ResponseEntity<Result<ContentDto>> modifyContent(@PathVariable Long contentId,
                                                            @ModelAttribute ContentReq requestDto,
                                                            @AuthenticationPrincipal User user){

        Content content = contentService.modifyContent(contentId, user, requestDto);

        return Result.toResult(ResultCode.MODIFY_SUCCESS, ContentDto.builder()
                                                        .id(content.getId()).build());
    }

    //정보 게시물 리스트 불러오기
    @GetMapping("/{categoryId}")
    public ResponseEntity<Result<List>> getContentsPerCategory(@PathVariable Long categoryId){
        List<ContentDto> contents = contentService.getContentsPerCategory(categoryId);
        return Result.toResult(ResultCode.LOAD_SUCCESS, contents);
    }

    //정보 게시물 상세보기
    @GetMapping("/detail/{contentId}")
    public ResponseEntity<Result<ContentDto>> getContent(@PathVariable Long contentId){
        Content content = contentService.getContent(contentId);
        return Result.toResult(ResultCode.LOAD_SUCCESS, new ContentDto().makeResponse(content));
    }

    //정보 게시물 수정하기
    @GetMapping("/like/{contentId}")
    public ResponseEntity<Result<ContentDto>> addLkie(@PathVariable Long contentId,
                                                            @AuthenticationPrincipal User user){

        Content content = contentService.addLike(contentId, user);

        return Result.toResult(ResultCode.ADD_SUCCESS, ContentDto.builder()
                                                .likecount(content.getLikeCount())
                                                .createdate(content.getCreateDate().toString()).build());
    }

    //정보 게시물 삭제하기
    @GetMapping("/delete/{contentId}")
    public ResponseEntity<Result> deleteContent(@PathVariable Long contentId,
                                                @AuthenticationPrincipal User user){
        contentService.deleteContent(contentId, user);

        return Result.toResult(ResultCode.DElETE_SUCCESS);
    }

}
