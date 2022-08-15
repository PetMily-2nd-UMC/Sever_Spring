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

        User testUser = new User("odie@gmail.com", passwordEncoder.encode("1234"),"odie", null, UserRoleEnum.USER, LoginEnum.General);
        User testUser2 = new User("odie1@gmail.com", passwordEncoder.encode("1234"),"odie1", null, UserRoleEnum.USER, LoginEnum.General);
        User testUser3 = new User("odie3@gmail.com", passwordEncoder.encode("1234"),"odie2", null, UserRoleEnum.USER, LoginEnum.General);
        User testUser4 = new User("odie4@gmail.com", passwordEncoder.encode("1234"),"odie3", null, UserRoleEnum.USER, LoginEnum.General);
        User testUser5 = new User("odie5@gmail.com", passwordEncoder.encode("1234"),"odie4", null, UserRoleEnum.USER, LoginEnum.General);
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


        /*Category category = categoryRepository.findById(2L)
                .orElseThrow(()->new IllegalArgumentException("카테고리가 없습니다."));*/


        Content content1 = new Content("테스트타이틀","테스트콘텐츠",null,category2,testUser);
        Content content2 = new Content("테스트타이틀2","테스트콘텐츠",null,category2,testUser);
        Content content3 = new Content("테스트타이틀4","테스트콘텐츠",null,category2,testUser);
        Content content4 = new Content("테스트타이틀5","테스트콘텐츠",null,category2,testUser);
        Content content5 = new Content("테스트타이틀1","테스트콘텐츠",null,category2,testUser);
        Content content6 = new Content("테스트타이틀2","테스트콘텐츠",null,category2,testUser);
        Content content7 = new Content("테스트타이틀1","테스트콘텐츠",null,category2,testUser);
        contentRepository.save(content1);
        contentRepository.save(content2);
        contentRepository.save(content3);
        contentRepository.save(content4);
        contentRepository.save(content5);
        contentRepository.save(content6);
        contentRepository.save(content7);

        Like like1 = new Like(content1, testUser2);
        Like like2 = new Like(content1, testUser3);
        Like like3 = new Like(content1, testUser4);
        Like like4 = new Like(content1, testUser5);

        Like like5 = new Like(content2, testUser2);
        Like like6 = new Like(content2, testUser3);
        Like like7 = new Like(content3, testUser3);
        Like like8 = new Like(content3, testUser5);

        Like like9 = new Like(content4, testUser2);
        Like like10 = new Like(content4, testUser3);
        Like like11 = new Like(content5, testUser2);
        Like like12 = new Like(content6, testUser3);


        likeRepository.save(like1);
        likeRepository.save(like2);
        likeRepository.save(like3);
        likeRepository.save(like4);
        likeRepository.save(like5);
        likeRepository.save(like6);
        likeRepository.save(like7);
        likeRepository.save(like8);
        likeRepository.save(like9);
        likeRepository.save(like10);
        likeRepository.save(like11);
        likeRepository.save(like12);

    }
}
