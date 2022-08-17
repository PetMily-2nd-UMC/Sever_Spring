package com.petmily.petmily.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.petmily.petmily.model.MyLog;
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
public class MyLogDto {

    private Long id;
//    private User user;
    private String name;
    private String imgUrl;
    private String videoUrl;

    public MyLogDto makeResponse(MyLog myLog) {
        return MyLogDto.builder()
                .id(myLog.getId())
//                .user(myLog.getUser())
                .imgUrl(myLog.getImgUrl())
                .videoUrl(myLog.getVideoUrl())
                .build();
    }


}
