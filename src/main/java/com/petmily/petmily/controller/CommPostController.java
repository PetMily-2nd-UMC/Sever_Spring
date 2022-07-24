package com.petmily.petmily.controller;

import com.petmily.petmily.dto.commPost.CommPostReq;
import com.petmily.petmily.model.commPost.CommPost;
import com.petmily.petmily.service.CommPostService;
import com.petmily.petmily.util.Result;
import com.petmily.petmily.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/commpost")
public class CommPostController {
    private final CommPostService commPostService;

    @Autowired
    public CommPostController(CommPostService commPostService) { this.commPostService = commPostService; }


    @PostMapping("/create")
    public ResponseEntity<Result> createPost(@RequestBody CommPostReq commPostReq){
        if(commPostService.createPost(commPostReq) == null) return Result.toResult(ResultCode.INTER_SERVER_ERROR);
        else return Result.toResult(ResultCode.CONTENT_CREATE_SUCCESS);
    }

    @GetMapping("/retrieveAll")
    public ResponseEntity<Result> retrieveAllPosts(){
        if(commPostService.retrieveAllPosts() == null) return Result.toResult(ResultCode.INTER_SERVER_ERROR);
        else return Result.toResult(ResultCode.CONTENT_RETRIEVE_SUCCESS);
    }

    @PatchMapping("/update")
    public ResponseEntity<Result> updatePost(@RequestBody CommPostReq commPostReq) {
        if(commPostService.updatePost(commPostReq) == null) return Result.toResult(ResultCode.INTER_SERVER_ERROR);
        else return Result.toResult(ResultCode.CONTENT_UPDATE_SUCCESS);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> deletePost(@RequestBody CommPostReq commPostReq) {
        if(commPostService.deletePost(commPostReq) == null) return Result.toResult(ResultCode.INTER_SERVER_ERROR);
        else return Result.toResult(ResultCode.CONTENT_DELETE_SUCCESS);
    }
}
