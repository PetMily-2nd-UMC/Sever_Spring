package com.petmily.petmily.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.petmily.petmily.model.MyLog;
import com.petmily.petmily.model.MyPage;
import com.petmily.petmily.model.MyPetmily;
import com.petmily.petmily.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class MyPageDto {

    private Long id;
    private User user;
    private String intro;
    private Long contentCount;
    private Long likeCount;
    private Long friendCount;
    private Long chatCount;
    private List<MyPetmily> myPetmily;
    private List<MyLog> myLog;

    public MyPageDto makeResponse(MyPage myPage) {
        return MyPageDto.builder()
                .id(myPage.getId())
                .user(myPage.getUser())
                .intro(myPage.getIntro())
                .contentCount(myPage.getContentCount())
                .likeCount(myPage.getLikeCount())
                .friendCount(myPage.getFriendCount())
                .chatCount(myPage.getChatCount())
                .myPetmily(getMyPetmily())
                .myLog(myPage.getMyLog())
                .build();
    }


}
