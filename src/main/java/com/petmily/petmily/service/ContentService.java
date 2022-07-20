package com.petmily.petmily.service;

import com.petmily.petmily.dto.ContentDto;
import com.petmily.petmily.model.Category;
import com.petmily.petmily.model.Content;
import com.petmily.petmily.model.User;
import com.petmily.petmily.repository.CategoryRepository;
import com.petmily.petmily.repository.ContentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final CategoryRepository categoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(ContentService.class);

    @Autowired
    public ContentService(ContentRepository contentRepository, CategoryRepository categoryRepository) {
        this.contentRepository = contentRepository;
        this.categoryRepository = categoryRepository;
    }


    @Transactional
    public Content createContent(Long categoryId, User user, ContentDto.Request requestDto) {
        String title = requestDto.getTitle();
        String contents = requestDto.getContent();
        String imgUrl = requestDto.getImgurl();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new IllegalArgumentException("카테고리가 없습니다."));

        Content content = new Content(title, contents, imgUrl, 0, category, user);
        return contentRepository.save(content);
    }


}
