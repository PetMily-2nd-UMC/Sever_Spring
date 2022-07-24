package com.petmily.petmily.repository.commPost;

import com.petmily.petmily.model.commPost.CommPostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CommPostLikeRepository extends JpaRepository<CommPostLike, Long> {
}
