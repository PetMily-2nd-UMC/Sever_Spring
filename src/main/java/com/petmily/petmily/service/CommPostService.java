package com.petmily.petmily.service;

import com.petmily.petmily.dto.commPost.CommPostReq;
import com.petmily.petmily.model.commPost.CommPost;
import com.petmily.petmily.model.commPost.CommPostComment;
import com.petmily.petmily.model.commPost.CommPostLike;
import com.petmily.petmily.repository.commPost.CommPostCommentRepository;
import com.petmily.petmily.repository.commPost.CommPostLikeRepository;
import com.petmily.petmily.repository.commPost.CommPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommPostService {

    private final CommPostRepository commPostRepository;
    private final CommPostCommentRepository commentRepository;
    private final CommPostLikeRepository likeRepository;

    @Autowired
    public CommPostService(CommPostRepository commPostRepository, CommPostCommentRepository commentRepository, CommPostLikeRepository likeRepository) {
        this.commPostRepository = commPostRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    public CommPost createPost(CommPostReq requestDto){
        if(requestDto.getComments() != null) {
            for (CommPostComment comment : requestDto.getComments()) { commentRepository.save(comment);}
        }
        if(requestDto.getLikes() != null) {
            for (CommPostLike like : requestDto.getLikes()){ likeRepository.save(like); }
        }
        return commPostRepository.save(requestDto.toEntity());
    }

    public List<CommPost> retrieveAllPosts() {
        return commPostRepository.findAll();
    }

    public CommPost updatePost(CommPostReq requestDto) {
        for (CommPostComment comment : requestDto.getComments()){ commentRepository.save(comment); }
        for (CommPostLike like : requestDto.getLikes()){ likeRepository.save(like); }
        return commPostRepository.save(requestDto.toEntity());
    }

    public CommPost deletePost(CommPostReq requestDto) {
        CommPost commPost = requestDto.toEntity();
        commPostRepository.delete(commPost);
        return commPost;
    }

}
