package com.petmily.petmily.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Content {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = true)
    private String imgUrl;

    @Column(nullable = false)
    private Integer likeCount;

    @CreationTimestamp
    private Timestamp createDate;

    @CreationTimestamp
    @Column(nullable = true)
    private Timestamp updateDate;

    @ManyToOne
    @JoinColumn(name = "category_no")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Content(String title, String content, String imgUrl, Integer likeCount, Category category, User user) {
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.likeCount = likeCount;
        this.category = category;
        this.user = user;
    }
}
