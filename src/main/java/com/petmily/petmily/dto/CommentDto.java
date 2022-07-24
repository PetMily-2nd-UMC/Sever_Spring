package com.petmily.petmily.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.petmily.petmily.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String text;
    private String createdate;
    //private Long contentid;
    private Long userid;
    private String nickname;
    private String profileurl;

    public CommentDto makeResponse(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .userid(comment.getUser().getId())
                .nickname(comment.getUser().getNickName())
                .profileurl(comment.getUser().getProfileUrl())
                .createdate(comment.getCreateDate().toString())
                .build();
    }
}