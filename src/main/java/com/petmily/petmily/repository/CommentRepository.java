package com.petmily.petmily.repository;

import com.petmily.petmily.model.Comment;
import com.petmily.petmily.model.Content;
import com.petmily.petmily.model.LevelEnum;
import com.petmily.petmily.model.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findById(Long commentId);

    HashSet<Comment> findByContentAndAndIsMommyOrderByIdAsc(Content content, LevelEnum levelEnum);

    HashSet<Comment> findByMommyCommentOrderByIdAsc(Comment comment);

    Optional<Comment> findByIdAndStatus(Long id, StatusEnum statusEnum);

}
