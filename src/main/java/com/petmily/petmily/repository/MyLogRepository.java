package com.petmily.petmily.repository;

import com.petmily.petmily.model.MyLog;
import com.petmily.petmily.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyLogRepository extends JpaRepository<MyLog, Long> {
    List<MyLog> findAllByUser(User user);
}
