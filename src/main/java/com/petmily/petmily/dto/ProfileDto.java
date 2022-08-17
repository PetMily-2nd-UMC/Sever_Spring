package com.petmily.petmily.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.petmily.petmily.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private Long userId;
    private String nickName;
    private String imgUrl;

    public ProfileDto makeResponse(User user){
        return ProfileDto.builder()
                .userId(user.getId())
                .nickName(user.getNickName())
                .imgUrl(user.getImgUrl())
                .build();
    }
}
