package com.petmily.petmily.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.petmily.petmily.model.Comment;
import com.petmily.petmily.model.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String text;
    private Integer likeCount;
    private Integer commentCount;
    private String createdate;
    //private Long contentid;
    private Long userid;
    private String nickname;
    private String profileurl;

    public CommentDto makeResponse(Comment comment){
        if(comment.getStatus().equals(StatusEnum.DELETED)){
            return CommentDto.builder()
                    .createdate(comment.getCreateDate().toString())
                    .text("삭제된 댓글입니다.")
                    .build();
        }
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .userid(comment.getUser().getId())
                .nickname(comment.getUser().getNickName())
                .profileurl(comment.getUser().getImgUrl())
                .commentCount(comment.getCommentCount())
                .likeCount(comment.getLikeCount())
                .createdate(comment.getCreateDate().toString())
                .build();
    }
}
