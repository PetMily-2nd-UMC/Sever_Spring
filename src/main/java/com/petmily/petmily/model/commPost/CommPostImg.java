package com.petmily.petmily.model.commPost;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommPostImg {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String url;


    @Builder
    public CommPostImg(String url) {
        this.url = url;
    }
}
