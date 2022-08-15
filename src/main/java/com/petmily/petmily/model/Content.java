package com.petmily.petmily.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    @Formula("(select count(1) from Likecontent l where l.content_id = id and l.status = 'ACTIVE')")
    private Integer likeCount;

    @Formula("(select count(1) from Comment c where c.content_id = id and c.status = 'ACTIVE')")
    private Integer commentCount;

    @CreationTimestamp
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "category_no")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    public Content(String title, String content, Category category, User user) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.status = StatusEnum.ACTIVE;
        this.user = user;
    }

    public void updateContent(String title, String content) {
        this.title = title;
        this.content = content;
        this.updateDate = new Timestamp(System.currentTimeMillis());
    }

    public void setDeleted(){
        this.status = StatusEnum.DELETED;
    }

    public void addImages(List<Image> images) {
        this.images = images;
    }

    public List<String> getImgUrls(){
        List<String> urls = new ArrayList<>();
        if(!this.images.isEmpty()){
            this.images.stream().forEach(image -> {
                urls.add(image.getUrl());
            });
            return urls;
        }
        return null;
    }
}
