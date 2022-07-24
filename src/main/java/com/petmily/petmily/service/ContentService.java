package com.petmily.petmily.service;

import com.petmily.petmily.dto.ContentDto;
import com.petmily.petmily.dto.ContentReq;
import com.petmily.petmily.model.*;
import com.petmily.petmily.repository.CategoryRepository;
import com.petmily.petmily.repository.ContentRepository;
import com.petmily.petmily.repository.LikeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final CategoryRepository categoryRepository;
    private final LikeRepository likeRepository;
    private static final Logger logger = LoggerFactory.getLogger(ContentService.class);

    @Autowired
    public ContentService(ContentRepository contentRepository, CategoryRepository categoryRepository, LikeRepository likeRepository) {
        this.contentRepository = contentRepository;
        this.categoryRepository = categoryRepository;
        this.likeRepository = likeRepository;
    }


    @Transactional
    public Content createContent(Long categoryId, User user, ContentReq requestDto) {
        String title = requestDto.getTitle();
        String contents = requestDto.getContent();
        String imgUrl = requestDto.getImgurl();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new IllegalArgumentException("카테고리가 없습니다."));

        Content content = new Content(title, contents, imgUrl, category, user);
        return contentRepository.save(content);
    }


    public List<ContentDto> getContentsPerCategory(Long categoryId) {
        Set<Content> contents = new HashSet<>();
        contents = contentRepository.findByCategoryNo(categoryId);
        return getContentList(contents);
    }

    public Content getContent(Long contentId) {
        return contentRepository.findById(contentId)
                .orElseThrow(()->new IllegalArgumentException("콘텐츠가 없습니다."));
    }

    public Content modifyContent(Long contentId, User user, ContentReq requestDto) {

       Content content = contentRepository.findById(contentId)
                .orElseThrow(()->new IllegalArgumentException("콘텐츠가 없습니다."));

        String title = requestDto.getTitle();
        String contents = requestDto.getContent();
        String imgUrl = requestDto.getImgurl();

       if(content.getUser().getId().equals(user.getId())){
           content.updateContent(title, contents, imgUrl);
           return contentRepository.save(content);
       }
       else {
           throw new IllegalArgumentException("게시물 수정 권한이 없습니다.");
       }
    }


    public List<ContentDto> getContentList(Set<Content> contentSet){
        List<ContentDto> list = new ArrayList<>();
        ContentDto contentDto = new ContentDto();
        contentSet.forEach(
                content -> {
                    ContentDto response=
                            contentDto.makeResponse(content);
                    list.add(response);
                }
        );

        return list;
    }

    public Content addLike(Long contentId, User user) {

        Content content = contentRepository.findById(contentId)
                .orElseThrow(()->new IllegalArgumentException("콘텐츠가 없습니다."));

        logger.error("addLike");
        Like userLike = new Like();

        if(!content.getUser().getId().equals(user.getId())){
            Optional<Like> like =likeRepository.findByContentIdAndUserId(contentId, user.getId());
            logger.error(like.toString());

            if(like.isEmpty()){
                logger.error("isempty");
                userLike = new Like(content, user);
                //likeRepository.save(new Like(content, user));
            }
            else if(like.get().getStatus() == StatusEnum.ACTIVE){
                userLike = like.get();
                userLike.setStatus(StatusEnum.DELETED);
                //likeRepository.save(like.get());
            }
            else if(like.get().getStatus() == StatusEnum.DELETED){
                userLike = like.get();
                userLike.setStatus(StatusEnum.ACTIVE);
            }
        }
        else {
            throw new IllegalArgumentException("자신의 글에 좋아요를 남길 수 없습니다.");
        }

        return likeRepository.save(userLike).getContent();
    }


    public void deleteContent(Long contentId, User user) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(()->new IllegalArgumentException("콘텐츠가 없습니다."));

        if(content.getUser().getId().equals(user.getId())){
            content.setDeleted();
            contentRepository.save(content);
        }
        else {
            throw new IllegalArgumentException("게시물 삭제 권한이 없습니다.");
        }

    }
}
