package com.petmily.petmily.repository;

import com.petmily.petmily.model.MyPage;
import com.petmily.petmily.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;

public interface MypageRepository extends JpaRepository<MyPage, Long> {
    MyPage findByUser(User user);
}
