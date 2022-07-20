package com.petmily.petmily.controller;

import com.petmily.petmily.dto.ContentDto;
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

    @PostMapping("/create/{categoryId}")
    public ResponseEntity<Result<Long>> createContent(@PathVariable Long categoryId,
                                                @RequestBody ContentDto.Request requestDto,
                                                @AuthenticationPrincipal User user){
        logger.error("createContent");
        Content content = contentService.createContent(categoryId, user, requestDto);
        //String result = "{" + "\n"+"\"contentId\": "+content.getId()+"\n" + "}";

        return Result.toResult(ResultCode.PUBLISH_SUCCESS, content.getId());
    }


    @PostMapping("/{categoryId}")
    public ResponseEntity<Result<List>> getContentsPerCategory(@PathVariable Long categoryId){
        List<ContentDto.Response> contents = contentService.getContentsPerCategory(categoryId);
        return Result.toResult(ResultCode.LOAD_SUCCESS, contents);
    }

}
