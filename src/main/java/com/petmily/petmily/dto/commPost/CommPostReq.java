package com.petmily.petmily.dto.commPost;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CommPostReq {
    private String title;
    private String content;
    private List<MultipartFile> imgs = new ArrayList<>();
}
