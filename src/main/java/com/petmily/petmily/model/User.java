package com.petmily.petmily.model;

import com.petmily.petmily.dto.LoginEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@NoArgsConstructor
@Entity
public class User implements UserDetails {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passWord;

    @Column(nullable = false, unique = true)
    private String nickName;

    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private Image image;

    @Column(nullable = false)
    private UserRoleEnum role;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LoginEnum login;


    @CreationTimestamp
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;

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
        return email;
    }

    public User(String email, String passWord, String nickName, UserRoleEnum role, LoginEnum login) {
        this.email = email;
        this.passWord = passWord;
        this.nickName = nickName;
        this.role = role;
        this.login = login;
    }

    public void addProfile(Image image) {
        this.image = image;
    }

    public String getImgUrl(){
        if(image != null){
            return image.getUrl();
        }
        return null;
    }
}
