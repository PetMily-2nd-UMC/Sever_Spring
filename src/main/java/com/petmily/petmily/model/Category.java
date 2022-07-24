package com.petmily.petmily.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) //값이 빈 객체 생성을 막음
@Entity
public class Category {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(nullable = false)
    private Long no;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CategoryEnum type;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private final Set<Content> contents = new HashSet<>();

    public Category(CategoryEnum type) {
        this.type = type;
    }
}
