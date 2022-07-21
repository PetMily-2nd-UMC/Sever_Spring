package com.petmily.petmily.util;

import com.petmily.petmily.model.Category;
import com.petmily.petmily.model.CategoryEnum;
import com.petmily.petmily.model.User;
import com.petmily.petmily.model.UserRoleEnum;
import com.petmily.petmily.repository.CategoryRepository;
import com.petmily.petmily.repository.ContentRepository;
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
    PasswordEncoder passwordEncoder;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        User testUser = new User("odie@gmail.com", passwordEncoder.encode("1234"),"odie", null, UserRoleEnum.USER);

        userRepository.save(testUser);


        Category category1 = new Category(1L, CategoryEnum.DOG);
        Category category2 = new Category(2L, CategoryEnum.CAT);
        Category category3 = new Category(3L, CategoryEnum.REPTILE);
        Category category4 = new Category(4L, CategoryEnum.FISH);
        Category category5 = new Category(5L, CategoryEnum.PARROT);
        Category category6 = new Category(6L, CategoryEnum.MOUSE);

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);
        categoryRepository.save(category5);
        categoryRepository.save(category6);

    }
}
