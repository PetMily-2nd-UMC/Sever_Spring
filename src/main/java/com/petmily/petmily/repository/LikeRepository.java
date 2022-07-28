package com.petmily.petmily.repository;

import com.petmily.petmily.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByContentIdAndUserId(Long contentId, Long userId);

    @Query(nativeQuery = true,
    value = "select l.content_id, count(*) as cnt from Likecontent l where l.create_date between :start and :end group by l.content_id order by cnt desc limit 5")
    List<Object[]> countLikeBetween(LocalDateTime start, LocalDateTime end);

}
