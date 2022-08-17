package com.petmily.petmily.dto.commPost;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.petmily.petmily.model.commPost.CommPost;
import com.petmily.petmily.model.commPost.CommPostImg;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class CommPostDto {

    private Long id;
    private String nickname;
    private String profileUrl;
    private boolean _isMyPost;
    private boolean _isBookmarked;
    private boolean _isLiked;

    private String title;
    private String content;
    private int likeCount;
    private int commentCount;

    private String createdDate;

    private List<CommPostImg> imgs;




    public CommPost toEntity(){
        CommPost commPost = CommPost.builder()
                ._isMyPost(_isMyPost)
                .profileUrl(profileUrl)
                ._isBookmarked(_isBookmarked)
                .title(title)
                .content(content)
                .likeCount(likeCount)
                .commentCount(commentCount)
                ._isLiked(_isLiked)
                .build();
        return commPost;
    }

    public CommPostDto makeResponse(CommPost post){
        return CommPostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .imgs(post.getImgs())
                .likeCount(post.getLikeCount())
                .commentCount(post.getCommentCount())
                .profileUrl(post.getProfileUrl())
                .createdDate(post.getCreatedDate().toString())
                .build();
    }


}
