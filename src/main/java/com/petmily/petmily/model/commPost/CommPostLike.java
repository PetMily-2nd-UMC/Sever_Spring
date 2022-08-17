package com.petmily.petmily.model.commPost;

import com.petmily.petmily.model.User;
import lombok.*;

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

    @ManyToOne
    private CommPost commPost;

    @ManyToOne
    private User user;

    @Builder
    public CommPostLike(String nickname, String likedAt) {
        this.nickname = nickname;
        this.likedAt = likedAt;
    }

    public CommPostLike(CommPost commPost, User user) {
        this.commPost = commPost;
        this.user = user;
    }
}
