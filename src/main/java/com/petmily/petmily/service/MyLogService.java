package com.petmily.petmily.service;

import com.petmily.petmily.dto.MyLogDto;
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
public class MyLogService {

    private final MyLogRepository myLogRepository;

    @Autowired
    public MyLogService(MyLogRepository myLogRepository) {
        this.myLogRepository = myLogRepository;
    }

    public List<MyLogDto> retrieveAllLog(User user) {
        return null;
//        return myLogRepository.findAllByUser(user);
    }
    public List<MyLog> retrieveAllLog() {
        return myLogRepository.findAll();
    }

    public MyLog createMyLog(User user, MyLogDto myLogDto) {
        String imgUrl = myLogDto.getImgUrl();
        String videoUrl = myLogDto.getVideoUrl();
        MyLog myLog = new MyLog(user, imgUrl, videoUrl);
//        MyLog myLog = new MyLog(imgUrl, videoUrl);
        return myLogRepository.save(myLog);
    }
}
