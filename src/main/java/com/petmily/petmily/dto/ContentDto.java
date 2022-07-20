package com.petmily.petmily.dto;

import lombok.*;


public class ContentDto {

    @Getter
    @Setter
    public static class Request{
        private String title;
        private String content;
        private String imgurl;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private Long id;
        private String title;
        private String content;
        private String imgurl;
        private Integer likecount;
        // private Long countcomment;
        private String createdate;
        private Long userid;
        private String nickname;
        private String profileurl;
    }

}
