package com.petmily.petmily.model.commPost;

import com.petmily.petmily.model.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommPost {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean _isMyPost;

    @Column(nullable = true)
    private String profileUrl;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean _isBookmarked;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int commentCount;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int likeCount;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean _isLiked;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(nullable = true)
    @ColumnDefault("null")
    @OneToMany
    private List<CommPostImg> imgs;

    @ManyToOne
    private User user;

    @Builder
    public CommPost(boolean _isMyPost, String profileUrl, boolean _isBookmarked, String title, String content,
                    int commentCount, int likeCount, boolean _isLiked, LocalDateTime createdDate, List<String> imgs){
        this._isMyPost = _isMyPost;
        this.profileUrl = profileUrl;
        this._isBookmarked = _isBookmarked;
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this._isLiked = _isLiked;
        this.createdDate = createdDate;
    }

    public CommPost(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void addImages(List<CommPostImg> imgs){ this.imgs = imgs; }

    public List<String> getImgUrls(){
        List<String> urls = new ArrayList<>();
        if (!this.imgs.isEmpty()) {
            this.imgs.stream().forEach(img -> {
                urls.add(img.getUrl());
            });
            return urls;
        }
        return null;
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
