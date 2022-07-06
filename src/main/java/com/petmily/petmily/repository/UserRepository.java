package com.petmily.petmily.repository;

import com.petmily.petmily.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
