package com.petmily.petmily.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ContentReq {
    private String title;
    private String content;
    private List<MultipartFile> image = new ArrayList<>();
}
