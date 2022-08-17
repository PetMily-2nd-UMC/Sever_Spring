package com.petmily.petmily.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MyLog {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @Column(nullable = true)
    private String imgUrl;

    @Column(nullable = true)
    private String videoUrl;

    public MyLog(User user, String imgUrl, String videoUrl) {
//    public MyLog(String imgUrl, String videoUrl) {
        this.user = user;
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
    }
}
