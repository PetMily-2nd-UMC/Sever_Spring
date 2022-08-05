package com.petmily.petmily.dto.commPost;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.petmily.petmily.model.commPost.CommPost;
import com.petmily.petmily.model.commPost.CommPostImg;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class CommPostReq {

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

    private LocalDateTime createdAt;

    private List<CommPostImg> imgs;

//    private List<CommPostComment> comments;
//    private List<CommPostLike> likes;



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
                .imgs(imgs)
//                .comments(comments)
//                .likes(likes)
                .createdAt(createdAt)
                .build();
        return commPost;
    }

    public CommPostReq makeResponse(CommPost post){
        return CommPostReq.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .imgs(post.getImgs())
                .likeCount(post.getLikeCount())
                .commentCount(post.getCommentCount())
//                .likes(post.getLikes())
//                .comments(post.getComments())
                .profileUrl(post.getProfileUrl())
                .createdAt(post.getCreatedAt())
                .build();
    }


}
