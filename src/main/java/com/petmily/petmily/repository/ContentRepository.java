package com.petmily.petmily.repository;

import com.petmily.petmily.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;

public interface ContentRepository extends JpaRepository<Content, Long> {

    HashSet<Content> findByCategoryNo(Long categoryNo);
}
