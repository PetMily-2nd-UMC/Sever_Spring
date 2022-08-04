package com.petmily.petmily.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
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
    private Timestamp createDate;

    @CreationTimestamp
    @Column(nullable = true)
    private Timestamp updateDate;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id", nullable = false)
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Comment(String text, Content content, User user) {
        this.text = text;
        this.content = content;
        this.status = StatusEnum.ACTIVE;
        this.user = user;
    }

    public void setDeleted(){
        this.status = StatusEnum.DELETED;
    }

}
