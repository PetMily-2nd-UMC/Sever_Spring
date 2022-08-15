package com.petmily.petmily.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petmily.petmily.config.JwtProvider;
import com.petmily.petmily.dto.LoginEnum;
import com.petmily.petmily.dto.SocialUserDto;
import com.petmily.petmily.dto.TokenDto;
import com.petmily.petmily.model.User;
import com.petmily.petmily.model.UserRoleEnum;
import com.petmily.petmily.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class KakaoUserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final String clientId;

    private static final Logger logger = LoggerFactory.getLogger(KakaoUserService.class);

    @Autowired
    public KakaoUserService(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtProvider jwtProvider, @Value("${oauth2.kakao.client-id}") String clientId) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.clientId = clientId;
    }

    public TokenDto loginUser(String code) throws JsonProcessingException {
        String accessToken = getAccessToken(code);
        SocialUserDto kakaoUserInfo = getKakaoUserInfo(accessToken);
        User kakaoUser = registerKakaoUserIfNeeded(kakaoUserInfo);

        return jwtProvider.generateToken(kakaoUser.getEmail());
    }

    private User registerKakaoUserIfNeeded(SocialUserDto kakaoUserInfo) {
        String email = kakaoUserInfo.getEmail();
        User kakaoUser = userRepository.findByEmail(email)
                .orElse(null);

        if(kakaoUser == null){
            String password = UUID.randomUUID().toString();
            String nickname = kakaoUserInfo.getNickname();
            String imgurl = null;
            UserRoleEnum role = UserRoleEnum.USER;

            kakaoUser = new User(email, passwordEncoder.encode(password), nickname, imgurl, role, LoginEnum.Kakao);

            return userRepository.save(kakaoUser);
        }

        return kakaoUser;
    }

    private String getAccessToken(String code) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type","authorization_code");
        body.add("client_id",clientId);
        body.add("redirect_uri","http://localhost:8080/user/kakao");
        body.add("code",code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.exchange("https://kauth.kakao.com/oauth/token",
                HttpMethod.POST, kakaoTokenRequest, String.class);

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        return jsonNode.get("access_token").asText();
    }

    private SocialUserDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.exchange("https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST, kakaoUserInfoRequest, String.class);

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        //logger.error(jsonNode.toString());
        String email = jsonNode.get("kakao_account").get("email").asText();
        String nickname = jsonNode.get("properties").get("nickname").asText();
        String imgUrl = jsonNode.get("properties").get("profile_image").asText();


        return new SocialUserDto(email, nickname);
    }
}
