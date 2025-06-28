package com.study.test.Common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler {

    //유효성 검사
    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse<String> throwException(MethodArgumentNotValidException e) {

        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(fieldError -> fieldError.getDefaultMessage())
                .orElse("유효성 검사 실패");


        return CommonResponse.error(errorMessage);
    }
    //비밀번호 다른 경우
    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse<String> throwException(BadCredentialsException e){
        log.error("BadCredentialsException={}", e);
        // 첫 번째 필드 에러 메시지 추출
        String errorMessage = e.getMessage();

        return CommonResponse.error(errorMessage);
    }


}
