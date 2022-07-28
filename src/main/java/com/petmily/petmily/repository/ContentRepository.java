package com.petmily.petmily.repository;

import com.petmily.petmily.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    HashSet<Content> findByCategoryNo(Long categoryNo);

    HashSet<Content> findByIdIn(List<Long> ids);

}
