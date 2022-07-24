package com.petmily.petmily.dto.commPost;

import com.petmily.petmily.model.commPost.CommPostImg;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Data
public class CommPostImgReq {
    private Long postImgId;
    private String url;

    public CommPostImg toEntity(){
        CommPostImg commPostImg = CommPostImg.builder()
                .url(url)
                .build();
        return commPostImg;
    }

    @Builder
    public CommPostImgReq(Long postImgId, String url) {
        this.postImgId = postImgId;
        this.url = url;
    }
}
