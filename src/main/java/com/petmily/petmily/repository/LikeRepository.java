package com.petmily.petmily.repository;

import com.petmily.petmily.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    //Optional<Like> findByContentIdAndUserId(Long contentId, Long userId);

    Optional<Like> findByContentIdAndUserId(Long contentId, Long userId);

}
