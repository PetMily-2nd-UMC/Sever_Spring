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
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.UUID;

@Service
public class GoogleUserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final String clientId;
    private final String clientSecret;

    @Autowired
    public GoogleUserService(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtProvider jwtProvider,
                             @Value("${oauth2.google.client-id}") String clientId, @Value("${oauth2.google.client-secret}") String clientSecret) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }


    public TokenDto loginUser(String code) throws JsonProcessingException {
        String accessToken  = getAccessToken(code);

        SocialUserDto googleUserInfo = getGoogleUserInfo(accessToken);

        User googleUser = registerGoogleUserIfNeeded(googleUserInfo);

        return jwtProvider.generateToken(googleUser.getEmail());

    }

    private User registerGoogleUserIfNeeded(SocialUserDto userInfo) {

        String email = userInfo.getEmail();
        User user = userRepository.findByEmail(email)
                .orElse(null);

        if(user == null){
            String password = UUID.randomUUID().toString();
            String nickname = userInfo.getNickname();
            String imgurl = null;
            UserRoleEnum role = UserRoleEnum.USER;

            user = new User(email, passwordEncoder.encode(password), nickname, role, LoginEnum.Google);

            return userRepository.save(user);
        }

        return user;

    }


    public String getAccessToken(String code) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type","authorization_code");
        body.add("client_id",clientId);
        body.add("client_secret",clientSecret);
        body.add("redirect_uri","http://localhost:8080/user/google");
        body.add("code",code);

        HttpEntity<MultiValueMap<String, String>> googleTokenRequest = new HttpEntity<>(body, headers);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.exchange("https://oauth2.googleapis.com/token",
                HttpMethod.POST, googleTokenRequest, String.class);

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);


        return jsonNode.get("access_token").asText();

    }

    private SocialUserDto getGoogleUserInfo(String accessToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        RestTemplate template = new RestTemplate();

        UriComponents uri = UriComponentsBuilder
                .fromUriString("https://www.googleapis.com/oauth2/v1/userinfo")
                .queryParam("alt","json")
                .queryParam("access_token",accessToken).build(true);

        ResponseEntity<String> response = template.exchange(uri.toUri(), HttpMethod.GET, new HttpEntity<>(headers), String.class);

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        String email = jsonNode.get("email").asText();
        String nickname = jsonNode.get("name").asText();
        String imgUrl = jsonNode.get("picture").asText();

        return new SocialUserDto(email, nickname);
    }
}
