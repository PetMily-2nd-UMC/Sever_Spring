package com.petmily.petmily.controller.commPost;

import com.petmily.petmily.dto.commPost.CommPostReq;
import com.petmily.petmily.model.commPost.CommPost;
import com.petmily.petmily.service.commPost.CommPostService;
import com.petmily.petmily.util.Result;
import com.petmily.petmily.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<CommPost> commPosts = commPostService.retrieveAllPosts();
        if(commPosts == null) return Result.toResult(ResultCode.INTER_SERVER_ERROR);
        else return Result.toResult(ResultCode.CONTENT_RETRIEVE_SUCCESS);
    }

    @PostMapping("/update/{postId}")
    public ResponseEntity<Result> updatePost(@PathVariable Long postId, @RequestBody CommPostReq commPostReq) {
        CommPost commPost = commPostService.updatePost(postId, commPostReq);
        if(commPost == null) return Result.toResult(ResultCode.INTER_SERVER_ERROR);
        else return Result.toResult(ResultCode.CONTENT_UPDATE_SUCCESS);
    }

    @GetMapping("/delete/{postId}")
    public ResponseEntity<Result> deletePost(@PathVariable Long postId) {
        if(commPostService.deletePost(postId) == null) return Result.toResult(ResultCode.INTER_SERVER_ERROR);
        else return Result.toResult(ResultCode.CONTENT_DELETE_SUCCESS);
    }
}
