package com.petmily.petmily.repository.commPost;

import com.petmily.petmily.model.commPost.CommPostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CommPostCommentRepository extends JpaRepository<CommPostComment, Long> {
}
