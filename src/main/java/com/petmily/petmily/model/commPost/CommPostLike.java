package com.petmily.petmily.model.commPost;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommPostLike {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String likedAt;


    @Builder
    public CommPostLike(String nickname, String likedAt) {
        this.nickname = nickname;
        this.likedAt = likedAt;
    }
}
