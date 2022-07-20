package com.petmily.petmily.controller;

import com.petmily.petmily.dto.ContentDto;
import com.petmily.petmily.model.Content;
import com.petmily.petmily.model.User;
import com.petmily.petmily.service.ContentService;
import com.petmily.petmily.util.Result;
import com.petmily.petmily.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content")
public class ContentController {
    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping("/create/{categoryId}")
    public ResponseEntity<Result<Long>> createContent(@PathVariable Long categoryId,
                                                @RequestBody ContentDto.Request requestDto,
                                                @AuthenticationPrincipal User user){
        Content content = contentService.createContent(categoryId, user, requestDto);
        return Result.toResult(ResultCode.PUBLISH_SUCCESS, content.getId());
    }

}
