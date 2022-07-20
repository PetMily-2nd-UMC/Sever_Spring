package com.petmily.petmily.repository;

import com.petmily.petmily.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {

}
