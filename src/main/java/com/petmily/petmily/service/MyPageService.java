package com.petmily.petmily.service;

import com.petmily.petmily.dto.MyPageDto;
import com.petmily.petmily.model.MyLog;
import com.petmily.petmily.model.MyPage;
import com.petmily.petmily.model.MyPetmily;
import com.petmily.petmily.model.User;
import com.petmily.petmily.repository.MyLogRepository;
import com.petmily.petmily.repository.MyPetmilyRepository;
import com.petmily.petmily.repository.MypageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MyPageService {

    private final MypageRepository mypageRepository;
    private final MyPetmilyRepository myPetmilyRepository;
    private final MyLogRepository myLogRepository;

    @Autowired
    public MyPageService(MypageRepository mypageRepository, MyPetmilyRepository myPetmilyRepository, MyLogRepository myLogRepository) {
        this.mypageRepository = mypageRepository;
        this.myPetmilyRepository = myPetmilyRepository;
        this.myLogRepository = myLogRepository;
    }

    public MyPage retrieveMypage(User user) {
        return mypageRepository.findByUser(user);
    }

    public MyPage createMypage(User user, MyPageDto myPageDto) {
        String intro = myPageDto.getIntro();
        Long contentCount = myPageDto.getContentCount();
        Long likeCount = myPageDto.getLikeCount();
        Long friendCount = myPageDto.getFriendCount();
        Long chatCount = myPageDto.getChatCount();

        List<MyPetmily> myPetmily = null;
        List<MyLog> myLog = null;

//        List<MyPetmily> myPetmily = myPetmilyRepository.findAllByUser(user);
//        List<MyLog> myLog = myLogRepository.findAllByUser(user);

        MyPage myPage = new MyPage(user, intro, contentCount, likeCount, friendCount, chatCount, myPetmily, myLog);
        return mypageRepository.save(myPage);
    }
}
