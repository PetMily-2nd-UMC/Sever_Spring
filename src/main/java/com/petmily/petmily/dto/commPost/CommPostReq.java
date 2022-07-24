package com.petmily.petmily.dto.commPost;

import com.petmily.petmily.model.commPost.CommPost;
import com.petmily.petmily.model.commPost.CommPostComment;
import com.petmily.petmily.model.commPost.CommPostImg;
import com.petmily.petmily.model.commPost.CommPostLike;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Data
@NoArgsConstructor
public class CommPostReq {

    private Long postId;
    private String nickname;
    private String profileUrl;
    private boolean _isMyPost;
    private boolean _isBookmarked;
    private boolean _isLiked;

    private String title;
    private String content;
    private int likeCount;
    private int commentCount;
    private List<CommPostImg> imgs;

    private List<CommPostComment> comments;
    private List<CommPostLike> likes;


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
                .comments(comments)
                .likes(likes)
                .build();
        return commPost;
    }

}
