package com.petmily.petmily.dto.commPost;

import com.petmily.petmily.model.commPost.CommPostLike;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class CommPostLikeReq {
    private Long id;
    private String nickname;
    private String likedAt;

    public CommPostLike toEntity(){
        CommPostLike commPostLike = CommPostLike.builder()
                .nickname(nickname)
                .likedAt(likedAt)
                .build();
        return commPostLike;
    }

}
