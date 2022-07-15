package com.petmily.petmily.repository;

import com.petmily.petmily.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Long findIdByUsername(String username);

    Optional<User> findByUsername(String username);

}
