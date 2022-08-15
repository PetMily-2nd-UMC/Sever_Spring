package com.petmily.petmily.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String text;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Formula("(select count(1) from Likecontent l where l.comment_id = id and l.status = 'ACTIVE')")
    private Integer likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id", nullable = false)
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mommy_id", nullable = true)
    private Comment mommyComment;

    @Enumerated(EnumType.STRING)
    private LevelEnum isMommy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Comment(String text, Content content, User user) {
        this.text = text;
        this.content = content;
        this.status = StatusEnum.ACTIVE;
        this.user = user;
        this.isMommy = LevelEnum.Y;
        //this.parentComment = this;
    }

    public Comment(String text, Content content, Comment parentComment, User user) {
        this.text = text;
        this.content = content;
        this.mommyComment = parentComment;
        this.status = StatusEnum.ACTIVE;
        this.user = user;
        this.isMommy = LevelEnum.N;
    }

    public void setDeleted(){
        this.status = StatusEnum.DELETED;
    }

}
