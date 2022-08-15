package com.petmily.petmily.repository;

import com.petmily.petmily.model.Comment;
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

    @Query(value = "select * from Comment c where c.id = :commentId and c.status = 'ACTIVE'"
            ,nativeQuery = true)
    Optional<Comment> findActiveComment(Long commentId);

    @Query(value = "select * from Comment c where c.content_id = :contentId and c.is_mommy = 'Y' order by c.create_date desc"
    ,nativeQuery = true)
    HashSet<Comment> findByContentId(Long contentId);

    @Query(value = "select * from Comment c where c.mommy_id = :mommyId order by c.create_date desc"
            ,nativeQuery = true)
    HashSet<Comment> findByMommyId(Long mommyId);

}
