package com.petmily.petmily.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class TopPostDto {

    private List<ContentDto> contents;
    private List<ContentDto> tmps; //초니님 형식에 맞게 수정해주세요.

}
