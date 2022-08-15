package com.petmily.petmily.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.petmily.petmily.model.Content;
import lombok.*;

import java.util.List;


@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ContentDto {

        private Long id;
        private String title;
        private String content;
        private String imgurl;
        private Integer likecount;
        private Integer countcomment;
        private String createdate;
        private Long userid;
        private String nickname;
        private List<String> imgs;
        private String profileUrl;

        public ContentDto makeResponse(Content content){
                return ContentDto.builder()
                        .id(content.getId())
                        .title(content.getTitle())
                        .content(content.getContent())
                        .likecount(content.getLikeCount())
                        .countcomment(content.getCommentCount())
                        .createdate(content.getCreateDate().toString())
                        .userid(content.getUser().getId())
                        .nickname(content.getUser().getNickName())
                        .profileUrl(content.getUser().getImgUrl())
                        .imgs(content.getImgUrls())
                        .build();
        }

}
