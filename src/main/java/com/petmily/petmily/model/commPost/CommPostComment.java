package com.petmily.petmily.model.commPost;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommPostComment {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String writtenAt;

    @Builder
    public CommPostComment(String nickname, String contents, String writtenAt) {
        this.nickname = nickname;
        this.contents = contents;
        this.writtenAt = writtenAt;
    }
}
