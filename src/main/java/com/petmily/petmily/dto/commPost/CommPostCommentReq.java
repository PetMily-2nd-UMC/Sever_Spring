package com.petmily.petmily.dto.commPost;

import com.petmily.petmily.model.commPost.CommPostComment;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class CommPostCommentReq {
    private Long id;
    private String nickname;
    private String contents;
    private String writtenAt;

    public CommPostComment toEntity(){
        CommPostComment commPostComment = CommPostComment.builder()
                .nickname(nickname)
                .contents(contents)
                .writtenAt(writtenAt)
                .build();
        return commPostComment;
    }

}
