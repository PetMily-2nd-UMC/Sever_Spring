package com.petmily.petmily.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class Result <T> {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final HttpStatus result;
    private final String code;
    private final String message;
    private final T data;


    public static ResponseEntity<Result> toResult(ResultCode resultCode){
        return ResponseEntity
                .status(resultCode.getHttpStatus())
                .body(Result.builder()
                .status(resultCode.getHttpStatus().value())
                .result(resultCode.getHttpStatus())
                .code(resultCode.name())
                .message(resultCode.getDetail())
                .build()
                );
    }

}
