package com.petmily.petmily.service;

import com.petmily.petmily.dto.CommentDto;
import com.petmily.petmily.model.*;
import com.petmily.petmily.repository.CommentRepository;
import com.petmily.petmily.repository.ContentRepository;
import com.petmily.petmily.repository.LikeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ContentRepository contentRepository;

    private final LikeRepository likeRepository;

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    public CommentService(CommentRepository commentRepository, ContentRepository contentRepository, LikeRepository likeRepository) {
        this.commentRepository = commentRepository;
        this.contentRepository = contentRepository;
        this.likeRepository = likeRepository;
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

    public List<CommentDto> createReComment(Long commentId, User user, String text) {

        Comment comment = commentRepository.findActiveComment(commentId)
                .orElseThrow(()->new IllegalArgumentException("코멘트가 없습니다."));

        commentRepository.save(new Comment(text, comment.getContent(), comment, user));

        Set<Comment> commentSet = commentRepository.findByMommyId(commentId);

        return getCommentList(commentSet);
    }

    public List<CommentDto> deleteComment(Long commentId, User user) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new IllegalArgumentException("댓글이 없습니다."));


        if(comment.getUser().getId().equals(user.getId())){
            comment.setDeleted();
            commentRepository.save(comment);
        }
        else {
            throw new IllegalArgumentException("게시물 삭제 권한이 없습니다.");
        }

        Set<Comment> commentSet = commentRepository.findByContentId(comment.getContent().getId());

        return getCommentList(commentSet);

    }

    public List<CommentDto> getContentComment(Long contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(()->new IllegalArgumentException("컨텐츠가 없습니다."));

        Set<Comment> commentSet = commentRepository.findByContentId(contentId);

        return getCommentList(commentSet);

    }

    public List<CommentDto> getReComment(Long commentId) {
        Comment comment = commentRepository.findActiveComment(commentId)
                .orElseThrow(()->new IllegalArgumentException("코멘트가 없습니다."));

        Content content = comment.getContent();

        Set<Comment> commentSet = commentRepository.findByMommyId(commentId);

        return getCommentList(commentSet);

    }

    public Comment addLike(Long commentId, User user) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new IllegalArgumentException("콘텐츠가 없습니다."));

        logger.error("addLike");
        Like userLike = new Like();

        if(!comment.getUser().getId().equals(user.getId())){
            Optional<Like> like =likeRepository.findByContentIdAndUserId(commentId, user.getId());
            logger.error(like.toString());

            if(like.isEmpty()){
                logger.error("isempty");
                userLike = new Like(comment, user);
            }
            else if(like.get().getStatus() == StatusEnum.ACTIVE){
                userLike = like.get();
                userLike.setStatus(StatusEnum.DELETED);
            }
            else if(like.get().getStatus() == StatusEnum.DELETED){
                userLike = like.get();
                userLike.setStatus(StatusEnum.ACTIVE);
            }
        }
        else {
            throw new IllegalArgumentException("자신의 글에 좋아요를 남길 수 없습니다.");
        }

        return likeRepository.save(userLike).getComment();
    }
}
