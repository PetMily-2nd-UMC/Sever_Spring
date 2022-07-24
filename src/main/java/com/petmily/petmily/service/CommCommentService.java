package com.petmily.petmily.service;

import com.petmily.petmily.dto.commPost.CommPostCommentReq;
import com.petmily.petmily.model.commPost.CommPostComment;
import com.petmily.petmily.repository.commPost.CommPostCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommCommentService {

    private final CommPostCommentRepository repository;

    @Autowired
    public CommCommentService(CommPostCommentRepository repository) {
        this.repository = repository;
    }

    public CommPostComment createComment(CommPostCommentReq requestDto){
        CommPostComment comment = requestDto.toEntity();
        repository.save(comment);
        return comment;
    }

    public List<CommPostComment> retrieveAllComments() {
        return repository.findAll();
    }

    public CommPostComment updateComment(CommPostCommentReq requestDto) {
        CommPostComment comment = requestDto.toEntity();
        repository.save(comment);
        return comment;
    }

    public CommPostComment deleteComment(CommPostCommentReq requestDto) {
        CommPostComment comment = requestDto.toEntity();
        System.out.println("(b)comment :: " + comment.getId());

        if(comment != null) repository.delete(comment);
        System.out.println("(a)acomment :: " + comment.getId());
        return comment;
    }

}
