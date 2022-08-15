package com.petmily.petmily.util;

import com.petmily.petmily.dto.LoginEnum;
import com.petmily.petmily.model.*;
import com.petmily.petmily.repository.CategoryRepository;
import com.petmily.petmily.repository.ContentRepository;
import com.petmily.petmily.repository.LikeRepository;
import com.petmily.petmily.repository.UserRepository;
import com.petmily.petmily.service.ContentService;
import com.petmily.petmily.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StartRunner implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ContentService contentService;

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        User testUser = new User("odie@gmail.com", passwordEncoder.encode("1234"),"odie",  UserRoleEnum.USER, LoginEnum.General);
        User testUser2 = new User("odie1@gmail.com", passwordEncoder.encode("1234"),"odie1", UserRoleEnum.USER, LoginEnum.General);
        User testUser3 = new User("odie3@gmail.com", passwordEncoder.encode("1234"),"odie2", UserRoleEnum.USER, LoginEnum.General);
        User testUser4 = new User("odie4@gmail.com", passwordEncoder.encode("1234"),"odie3",  UserRoleEnum.USER, LoginEnum.General);
        User testUser5 = new User("odie5@gmail.com", passwordEncoder.encode("1234"),"odie4", UserRoleEnum.USER, LoginEnum.General);
        userRepository.save(testUser);
        userRepository.save(testUser2);
        userRepository.save(testUser3);
        userRepository.save(testUser4);
        userRepository.save(testUser5);


        Category category1 = new Category(CategoryEnum.DOG);
        Category category2 = new Category(CategoryEnum.CAT);
        Category category3 = new Category(CategoryEnum.REPTILE);
        Category category4 = new Category(CategoryEnum.FISH);
        Category category5 = new Category(CategoryEnum.PARROT);
        Category category6 = new Category(CategoryEnum.MOUSE);

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);
        categoryRepository.save(category5);
        categoryRepository.save(category6);


    }
}
