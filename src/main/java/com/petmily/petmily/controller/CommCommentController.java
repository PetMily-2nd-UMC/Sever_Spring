package com.petmily.petmily.controller;

import com.petmily.petmily.dto.commPost.CommPostCommentReq;
import com.petmily.petmily.service.CommCommentService;
import com.petmily.petmily.util.Result;
import com.petmily.petmily.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commpost-comment")
public class CommCommentController {
    private final CommCommentService commentService;

    @Autowired
    public CommCommentController(CommCommentService commentService) { this.commentService = commentService; }


    @PostMapping("/create")
    public ResponseEntity<Result> createComment(@RequestBody CommPostCommentReq commentReq){
        if(commentService.createComment(commentReq) == null) return Result.toResult(ResultCode.INTER_SERVER_ERROR);
        else return Result.toResult(ResultCode.CONTENT_CREATE_SUCCESS);
    }

    @GetMapping("/retrieveAll")
    public ResponseEntity<Result> retrieveAllComments(){
        if(commentService.retrieveAllComments() == null) return Result.toResult(ResultCode.INTER_SERVER_ERROR);
        else return Result.toResult(ResultCode.CONTENT_RETRIEVE_SUCCESS);
    }

    @PatchMapping("/update")
    public ResponseEntity<Result> updatePost(@RequestBody CommPostCommentReq commentReq) {
        return Result.toResult(ResultCode.CONTENT_UPDATE_SUCCESS);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> deletePost(@RequestBody CommPostCommentReq commentReq) {
        if(commentService.deleteComment(commentReq) == null) return Result.toResult(ResultCode.INTER_SERVER_ERROR);
        else return Result.toResult(ResultCode.CONTENT_DELETE_SUCCESS);
    }
}
