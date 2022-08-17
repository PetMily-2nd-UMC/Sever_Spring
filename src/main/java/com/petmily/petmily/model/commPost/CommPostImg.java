package com.petmily.petmily.model.commPost;

import com.petmily.petmily.model.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommPostImg {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    private CommPost commPost;

    @ManyToOne
    private User user;

    @CreationTimestamp
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp updatedDate;

    @Builder
    public CommPostImg(String url, User user) {
        this.url = url;
        this.user = user;
    }

    public CommPostImg(String url, CommPost commPost) {
        this.url = url;
        this.commPost = commPost;
    }
}
