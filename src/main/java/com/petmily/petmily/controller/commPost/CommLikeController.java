package com.petmily.petmily.controller.commPost;

import com.petmily.petmily.dto.commPost.CommPostLikeReq;
import com.petmily.petmily.service.commPost.CommLikeService;
import com.petmily.petmily.util.Result;
import com.petmily.petmily.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commpost-like")
public class CommLikeController {
    private final CommLikeService likeService;

    @Autowired
    public CommLikeController(CommLikeService likeService) { this.likeService = likeService; }


    @PostMapping("/create")
    public ResponseEntity<Result> createLike(@RequestBody CommPostLikeReq likeReq){
        if(likeService.createLike(likeReq) == null) return Result.toResult(ResultCode.INTER_SERVER_ERROR);
        else return Result.toResult(ResultCode.CONTENT_CREATE_SUCCESS);
    }

    @GetMapping("/retrieveAll")
    public ResponseEntity<Result> retrieveAllLikes(){
        if(likeService.retrieveAllLikes() == null) return Result.toResult(ResultCode.INTER_SERVER_ERROR);
        else return Result.toResult(ResultCode.CONTENT_RETRIEVE_SUCCESS);
    }

    @PatchMapping("/update")
    public ResponseEntity<Result> updateLike(@RequestBody CommPostLikeReq likeReq) {
        return Result.toResult(ResultCode.CONTENT_UPDATE_SUCCESS);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> deleteLike(@RequestBody CommPostLikeReq likeReq) {
        if(likeService.deleteLike(likeReq) == null) return Result.toResult(ResultCode.INTER_SERVER_ERROR);
        else return Result.toResult(ResultCode.CONTENT_DELETE_SUCCESS);
    }
}
