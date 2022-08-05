package com.petmily.petmily.model.commPost;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    private LocalDateTime createdAt;

    @Column(nullable = true)
    @ColumnDefault("null")
    @OneToMany
    private List<CommPostImg> imgs;


//    @Column(nullable = true)
//    @ColumnDefault("null")
//    @OneToMany
//    private List<CommPostComment> comments;
//
//    @Column(nullable = true)
//    @ColumnDefault("null")
//    @OneToMany
//    private List<CommPostLike> likes;


    @Builder
    public CommPost(boolean _isMyPost, String profileUrl, boolean _isBookmarked, String title, String content,
                    int commentCount, int likeCount, boolean _isLiked, LocalDateTime createdAt, List<CommPostImg> imgs){
//                    List<CommPostComment> comments, List<CommPostLike> likes) {
        this._isMyPost = _isMyPost;
        this.profileUrl = profileUrl;
        this._isBookmarked = _isBookmarked;
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this._isLiked = _isLiked;
        this.createdAt = createdAt;
        this.imgs = imgs;
//        this.comments = comments;
//        this.likes = likes;
    }
}
