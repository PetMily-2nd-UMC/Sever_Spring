package com.petmily.petmily.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "LIKECONTENT")
public class Like {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @CreationTimestamp
    private Timestamp createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id", nullable = false)
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Like(Content content, User user) {
        this.content = content;
        this.user = user;
        this.status = StatusEnum.ACTIVE;
    }

   public void setStatus(StatusEnum status){
       this.status = status;
   }
}
