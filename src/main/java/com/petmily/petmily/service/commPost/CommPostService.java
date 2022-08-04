package com.petmily.petmily.service.commPost;

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

    public CommPost updatePost(Long postId, CommPostReq requestDto) {
        CommPost commPost = commPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 없습니다"));

        commPost.setTitle(requestDto.getTitle());
        commPost.setContent(requestDto.getContent());
        commPost.setImgs(requestDto.getImgs());

        commPostRepository.save(commPost);
        return commPost;
    }

    public CommPost deletePost(Long postId) {
        System.out.println("postId = " + postId);
        CommPost commPost = commPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 없습니다"));

        commPostRepository.deleteById(postId);
        return commPost;
    }

}
