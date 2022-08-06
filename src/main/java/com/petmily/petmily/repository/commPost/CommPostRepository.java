package com.petmily.petmily.repository.commPost;

import com.petmily.petmily.model.commPost.CommPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Repository
public interface CommPostRepository extends JpaRepository<CommPost, Long> {
    HashSet<CommPost> findByIdIn(List<Long> ids);
    @Query(nativeQuery = true,
            value = "select cp.id, cp.like_count as cnt from comm_post cp where cp.created_at between :start and :end order by cp.like_count desc limit 5")
    List<Object[]> countLikeBetween(LocalDateTime start, LocalDateTime end);
}