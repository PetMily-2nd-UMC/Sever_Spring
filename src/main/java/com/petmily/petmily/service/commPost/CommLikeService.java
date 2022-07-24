package com.petmily.petmily.service.commPost;

import com.petmily.petmily.dto.commPost.CommPostLikeReq;
import com.petmily.petmily.model.commPost.CommPostLike;
import com.petmily.petmily.repository.commPost.CommPostLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommLikeService {

    private final CommPostLikeRepository repository;

    @Autowired
    public CommLikeService(CommPostLikeRepository repository) {
        this.repository = repository;
    }

    public CommPostLike createLike(CommPostLikeReq requestDto){
        CommPostLike like = requestDto.toEntity();
        repository.save(like);
        return like;
    }

    public List<CommPostLike> retrieveAllLikes() {
        return repository.findAll();
    }

    public CommPostLike updateLike(CommPostLikeReq requestDto) {
        CommPostLike like = requestDto.toEntity();
        repository.save(like);
        return like;
    }

    public CommPostLike deleteLike(CommPostLikeReq requestDto) {
        CommPostLike like = requestDto.toEntity();
        if(like != null) repository.delete(like);
        return like;
    }

}
