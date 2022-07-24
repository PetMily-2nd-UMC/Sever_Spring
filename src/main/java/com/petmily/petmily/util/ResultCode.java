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
    SIGNUP_SUCCESS(OK, "회원가입성공"),

    CONTENT_CREATE_SUCCESS(OK, "등록 성공"),
    CONTENT_RETRIEVE_SUCCESS(OK, "조회 성공"),
    CONTENT_UPDATE_SUCCESS(OK, "수정 성공"),
    CONTENT_DELETE_SUCCESS(OK, "삭제 성공"),
    
    PUBLISH_SUCCESS(OK,"게시물 등록"),
    MODIFY_SUCCESS(OK,"게시물 수정"),
    DElETE_SUCCESS(OK,"게시물 삭제"),
    LOAD_SUCCESS(OK,"게시물 로드"),
    ADD_SUCCESS(OK,"게시물에 반응"),
    
    /* 500 CONFLICT */
    INTER_SERVER_ERROR(INTERNAL_SERVER_ERROR, "서버 오류 발생"),
    ;
    private final HttpStatus httpStatus;
    private final String detail;

}
