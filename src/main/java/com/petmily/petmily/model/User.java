package com.petmily.petmily.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@DynamicInsert
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = true)
    private String profileUrl;

    @Column(nullable = false)
    private UserRoleEnum role;


    @Builder    //
    public User(String username, String password, String nickname, String profileUrl, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.role = role;
    }



}
