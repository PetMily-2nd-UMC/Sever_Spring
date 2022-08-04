package com.petmily.petmily.controller;

import com.petmily.petmily.dto.CommentDto;
import com.petmily.petmily.dto.ContentDto;
import com.petmily.petmily.model.Comment;
import com.petmily.petmily.model.User;
import com.petmily.petmily.service.CommentService;
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
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //정보 게시물 댓글 등록
    @PostMapping("/{contentId}")
    public ResponseEntity<Result<List<CommentDto>>> createComment(@PathVariable Long contentId,
                                                            @RequestParam("body") String text,
                                                            @AuthenticationPrincipal User user){

        List<CommentDto> comments = commentService.createComment(contentId, user, text);

        return Result.toResult(ResultCode.ADD_SUCCESS, comments);
    }

    //정보 게시물 댓글 삭제
    @PostMapping("/delete/{commentId}")
    public ResponseEntity<Result<List<CommentDto>>> deleteComment(@PathVariable Long commentId,
                                                                  @AuthenticationPrincipal User user){
        List<CommentDto> comments = commentService.deleteComment(commentId, user);
        return Result.toResult(ResultCode.DElETE_SUCCESS, comments);
    }

}
