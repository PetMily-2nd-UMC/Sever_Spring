package com.petmily.petmily.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
public enum ResultCode {
    /* 200 */
    LOGIN_SUCCESS(OK, "로그인 성공"),
    SIGNUP_SUCCESS(OK, "회원가입 성공"),
    PUBLISH_SUCCESS(OK,"게시물 등록"),
    /* 500 CONFLICT */
    INTER_SERVER_ERROR(INTERNAL_SERVER_ERROR, "서버 오류 발생"),
    ;
    private final HttpStatus httpStatus;
    private final String detail;

}
