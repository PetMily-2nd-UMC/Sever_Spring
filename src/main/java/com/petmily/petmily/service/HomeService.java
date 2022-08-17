package com.petmily.petmily.service;

import com.petmily.petmily.dto.ContentDto;
import com.petmily.petmily.dto.commPost.CommPostReq;
import com.petmily.petmily.model.Content;
import com.petmily.petmily.model.commPost.CommPost;
import com.petmily.petmily.repository.ContentRepository;
import com.petmily.petmily.repository.LikeRepository;
import com.petmily.petmily.repository.commPost.CommPostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HomeService {
    private final ContentRepository contentRepository;
    private final LikeRepository likeRepository;
    private final CommPostRepository commPostRepository;


    private static final Logger logger = LoggerFactory.getLogger(HomeService.class);

    @Autowired
    public HomeService(ContentRepository contentRepository, LikeRepository likeRepository, CommPostRepository commPostRepository) {
        this.contentRepository = contentRepository;
        this.likeRepository = likeRepository;
        this.commPostRepository = commPostRepository;
    }

    public List<ContentDto> getTopContent(){
        LocalDateTime now = LocalDateTime.now();
        List<Object[]> list = likeRepository.countLikeBetween(now.minusHours(1), now);
        List<Long> contentIds = new ArrayList<>();

        if(!list.isEmpty()) {
            for (Object[] row : list) {
                Object contentId = row[0];
                contentIds.add(Long.valueOf(String.valueOf(contentId)));
            }
            Set<Content> contents = new HashSet<>();
            contents = contentRepository.findByIdIn(contentIds);
            return getContentList(contents);
        }
        return null;
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

    public List<CommPostReq> getTopCommPost() {
        LocalDateTime now = LocalDateTime.now();
        List<Object[]> list = commPostRepository.countLikeBetween(now.minusHours(1), now);
        List<Long> contentIds = new ArrayList<>();

        if(!list.isEmpty()) {
            for (Object[] row : list) {
                Object contentId = row[0];
                contentIds.add(Long.valueOf(String.valueOf(contentId)));
            }
            Set<CommPost> posts = new HashSet<>();
            posts = commPostRepository.findByIdIn(contentIds);
            return getCommPostList(posts);
        }
        return null;
    }

    public List<CommPostReq> getCommPostList(Set<CommPost> postSet) {
        List<CommPostReq> list = new ArrayList<>();
        CommPostReq commPostReq = new CommPostReq();
        postSet.forEach(
                commPost -> {
                    CommPostReq response = commPostReq.makeResponse(commPost);
                    list.add(response);
                }
        );
        return list;
    }

}
