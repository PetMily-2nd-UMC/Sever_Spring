package com.petmily.petmily.repository;

import com.petmily.petmily.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    //HashSet<Comment> getByContentId(Long contentId);

    Optional<Comment> findByContentIdAndUserId(Long contentId, Long userId);

    HashSet<Comment> findByContentId(Long contentId);
}
