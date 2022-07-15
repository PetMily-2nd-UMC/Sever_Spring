package com.petmily.petmily.repository;

import com.petmily.petmily.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByuserName(String userName);

    @Query("select u.id from User u where u.userName = :userName")
    Optional<Long> findIdByuserName(@Param("userName") String userName);

}
