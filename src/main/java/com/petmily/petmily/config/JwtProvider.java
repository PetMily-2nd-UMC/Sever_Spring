package com.petmily.petmily.config;

import com.petmily.petmily.dto.TokenDto;
import com.petmily.petmily.model.User;
import com.petmily.petmily.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Component
public class JwtProvider {

    private final String jwtSecret;
    public final long ACCESS_TOKEN_VALID_PERIOD = 1000L*60*30;
    public final long REFRESH_TOKEN_VALID_PERIOD = 1000L*60*60*24*7;
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    public JwtProvider(@Value("${jwt.secret}") String jwtSecret, UserRepository userRepository) {
        this.jwtSecret = jwtSecret;
        this.userRepository = userRepository;
    }


    @Transactional(readOnly = true)
    public TokenDto generateToken(String email){
        Date now = new Date();
        Long userId = userRepository.findIdByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));

        Claims claims = Jwts.claims().setSubject(userId.toString());
        Date accessTokenExpireIn = new Date(now.getTime() + ACCESS_TOKEN_VALID_PERIOD);

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(accessTokenExpireIn)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        String refreshToken = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALID_PERIOD))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();


        return new TokenDto(accessToken, refreshToken, accessTokenExpireIn.getTime());
    }

    public String getUserId(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication getAuthentication(String token){
        String userId = getUserId(token);
        User user = userRepository.findById(Long.parseLong(userId)).orElseThrow();

        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    public boolean isTokenValid(String token) {
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (JwtException e){
            e.printStackTrace();
        }
        return false;
    }

}
