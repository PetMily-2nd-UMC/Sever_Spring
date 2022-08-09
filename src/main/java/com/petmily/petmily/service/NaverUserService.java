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

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

@Service
public class NaverUserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    private final String clientId;

    private final String clientSecret;

    private static final Logger logger = LoggerFactory.getLogger(NaverUserService.class);

    @Autowired
    public NaverUserService(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtProvider jwtProvider, @Value("${oauth2.naver.client-id}") String clientId, @Value("${oauth2.naver.client-secret}") String clientSecret) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public TokenDto loginUser(String code, String state) throws JsonProcessingException {
        String accessToken = getAccessToken(code, state);

        SocialUserDto naverUserInfo = getNaverUserInfo(accessToken);

        User naverUser = registerNaverUserIfNeeded(naverUserInfo);

        return jwtProvider.generateToken(naverUser.getEmail());
    }

    private User registerNaverUserIfNeeded(SocialUserDto naverUserInfo) {

        String email = naverUserInfo.getEmail();
        User naverUser = userRepository.findByEmail(email)
                .orElse(null);

        if(naverUser == null){
            String password = UUID.randomUUID().toString();
            String nickname = naverUserInfo.getNickname();
            String imgurl = null;
            UserRoleEnum role = UserRoleEnum.USER;

            naverUser = new User(email, passwordEncoder.encode(password), nickname, imgurl, role, LoginEnum.Naver);

            return userRepository.save(naverUser);
        }

        return naverUser;
    }

    private String getAccessToken(String code, String state) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type","authorization_code");
        body.add("client_id",clientId);
        body.add("client_secret",clientSecret);
        body.add("redirect_uri","http://localhost:8080/user/naver");
        body.add("code",code);
        body.add("state",state);

        HttpEntity<MultiValueMap<String, String>> naverTokenRequest = new HttpEntity<>(body, headers);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.exchange("https://nid.naver.com/oauth2.0/token",
                HttpMethod.POST, naverTokenRequest, String.class);

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        return jsonNode.get("access_token").asText();

    }

    private SocialUserDto getNaverUserInfo(String accessToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> naverUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.exchange("https://openapi.naver.com/v1/nid/me",
                HttpMethod.POST, naverUserInfoRequest, String.class);

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        //System.out.println(jsonNode.toString());
        String email = jsonNode.get("response").get("email").asText();
        String nickname = jsonNode.get("response").get("nickname").asText();
        String imgUrl = jsonNode.get("response").get("profile_image").asText();


        return new SocialUserDto(email, nickname);

    }

}
