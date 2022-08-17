package com.petmily.petmily.repository;

import com.petmily.petmily.model.MyPetmily;
import com.petmily.petmily.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyPetmilyRepository extends JpaRepository<MyPetmily, Long> {
    List<MyPetmily> findAllByUser(User user);
}
