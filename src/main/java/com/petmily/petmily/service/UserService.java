package com.petmily.petmily.service;

import com.petmily.petmily.dto.SignupReq;
import com.petmily.petmily.model.User;
import com.petmily.petmily.model.UserRoleEnum;
import com.petmily.petmily.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
   private final PasswordEncoder passwordEncoder;
   private final UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User registerUser(SignupReq requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String nickname = requestDto.getNickname();
        String imgurl = requestDto.getImgUrl();
        UserRoleEnum role = UserRoleEnum.USER;

        User user = new User(username, password, nickname, imgurl, role);
        return userRepository.save(user);
    }
}
