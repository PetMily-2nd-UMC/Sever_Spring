package com.petmily.petmily.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class User implements UserDetails {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String passWord;

    @Column(nullable = false, unique = true)
    private String nickName;

    @Column(nullable = true)
    private String profileUrl;

    @Column(nullable = false)
    private UserRoleEnum role;


    @CreationTimestamp
    private Timestamp createDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role.toString();
            }
        });
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getPassword() {
        return passWord;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public User(String userName, String passWord, String nickName, String profileUrl, UserRoleEnum role) {
        this.userName = userName;
        this.passWord = passWord;
        this.nickName = nickName;
        this.profileUrl = profileUrl;
        this.role = role;
    }

}
