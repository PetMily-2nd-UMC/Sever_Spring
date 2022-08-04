package com.petmily.petmily.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.sql.Date;
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

    @Formula("(select count(1) from Likecontent l where l.content_id = id and l.status = 'ACTIVE')")
    private Integer likeCount;

    @Formula("(select count(1) from Comment c where c.content_id = id and c.status = 'ACTIVE')")
    private Integer commentCount;

    @CreationTimestamp
    private Timestamp createDate;

    @CreationTimestamp
    @Column(nullable = true)
    private Timestamp updateDate;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "category_no")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Content(String title, String content, String imgUrl, Category category, User user) {
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.category = category;
        this.status = StatusEnum.ACTIVE;
        this.user = user;
    }

    public void updateContent(String title, String content, String imgUrl) {
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.updateDate = new Timestamp(System.currentTimeMillis());
    }

    public void setDeleted(){
        this.status = StatusEnum.DELETED;
    }
}
