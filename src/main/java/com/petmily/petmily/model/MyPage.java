package com.petmily.petmily.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MyPage {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    @Column(nullable = false)
    private String profileUrl;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String intro;

    @Column(nullable = false)
    private Long contentCount;

    @Column(nullable = false)
    private Long likeCount;

    @Column(nullable = false)
    private Long friendCount;

    @Column(nullable = false)
    private Long chatCount;

    @OneToMany
    private List<MyPetmily> myPetmily;

    @OneToMany
    private List<MyLog> myLog;


    public MyPage(User user, String intro, Long contentCount, Long likeCount, Long friendCount, Long chatCount, List<MyPetmily> myPetmily, List<MyLog> myLog) {
        this.user = user;
        this.intro = intro;
        this.contentCount = contentCount;
        this.likeCount = likeCount;
        this.friendCount = friendCount;
        this.chatCount = chatCount;
        this.myPetmily = myPetmily;
        this.myLog = myLog;
    }
}
