package com.petmily.petmily.repository;

import com.petmily.petmily.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //Optional<User> findByemail(String email);
    Optional<User> findByEmail(String email);

    @Query("select u.id from User u where u.email = :email")
    Optional<Long> findIdByEmail(@Param("email") String email);


}
