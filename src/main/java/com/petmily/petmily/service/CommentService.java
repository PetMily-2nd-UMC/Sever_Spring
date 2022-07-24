package com.petmily.petmily.service;

import com.petmily.petmily.dto.CommentDto;
import com.petmily.petmily.model.Comment;
import com.petmily.petmily.model.Content;
import com.petmily.petmily.model.User;
import com.petmily.petmily.repository.CommentRepository;
import com.petmily.petmily.repository.ContentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ContentRepository contentRepository;
    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    public CommentService(CommentRepository commentRepository, ContentRepository contentRepository) {
        this.commentRepository = commentRepository;
        this.contentRepository = contentRepository;
    }


    public List<CommentDto> getCommentList(Set<Comment> commentSet){
        List<CommentDto> list = new ArrayList<>();
        CommentDto commentDto = new CommentDto();
        commentSet.forEach(
                comment -> {
                    CommentDto response =
                            commentDto.makeResponse(comment);
                    list.add(response);
                }
        );
        return list;
    }

    public List<CommentDto> createComment(Long contentId, User user, String text) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(()->new IllegalArgumentException("컨텐츠가 없습니다."));

        commentRepository.save(new Comment(text, content, user));

        Set<Comment> commentSet = commentRepository.findByContentId(contentId);

        return getCommentList(commentSet);
    }

    public List<CommentDto> deleteComment(Long contentId, User user) {
        Comment comment = commentRepository.findByContentIdAndUserId(contentId, user.getId())
                .orElseThrow(()->new IllegalArgumentException("댓글이 없습니다."));

        comment.setDeleted();

        commentRepository.save(comment);

        Set<Comment> commentSet = commentRepository.findByContentId(contentId);

        return getCommentList(commentSet);

    }
}
