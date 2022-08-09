package com.petmily.petmily.service;

import com.petmily.petmily.config.JwtProvider;
import com.petmily.petmily.dto.LoginEnum;
import com.petmily.petmily.dto.LoginReq;
import com.petmily.petmily.dto.SignupReq;
import com.petmily.petmily.dto.TokenDto;
import com.petmily.petmily.model.User;
import com.petmily.petmily.model.UserRoleEnum;
import com.petmily.petmily.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
   private final PasswordEncoder passwordEncoder;
   private final UserRepository userRepository;
   private final JwtProvider jwtProvider;
   private static final Logger logger = LoggerFactory.getLogger(UserService.class);

   @Value("${spring.admin}")
   private static String ADMIN_KEY;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtProvider jwtProvider) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public User registerUser(SignupReq requestDto) {
        String email = requestDto.getEmail();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String nickname = requestDto.getNickname();
        String imgurl = requestDto.getImgUrl();
        UserRoleEnum role = UserRoleEnum.USER;
        logger.error("registeruser: "+email);

        if(requestDto.isAdmin()){
            //나중에 수정
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(email, password, nickname, imgurl, role, LoginEnum.General);
        return userRepository.save(user);
    }

    public TokenDto loginUser(LoginReq requestDto) {

        //logger.error("loginuser: "+requestDto.getEmail());

        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(requestDto.getEmail()))
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));

        if(user.isPresent()){
            //logger.error("ispresent: ");
            if(passwordEncoder.matches(requestDto.getPassword(), user.get().getPassword())){
                /*TokenDto tokenDto = jwtProvider.generateToken(requestDto.getEmail());
                logger.error("password: "+requestDto.getPassword());*/
                return jwtProvider.generateToken(requestDto.getEmail());
            }
            return null;
        }
        return null;
    }


}
