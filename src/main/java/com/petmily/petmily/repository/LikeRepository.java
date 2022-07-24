package com.petmily.petmily.repository;

import com.petmily.petmily.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByContentIdAndUserId(Long contentId, Long userId);

}
