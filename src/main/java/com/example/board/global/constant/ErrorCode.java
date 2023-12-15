package com.example.board.global.constant;

import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@Generated
public enum ErrorCode {

    INVALID_VALUE(HttpStatus.BAD_REQUEST,"잘못된 입력값입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러"),


    INVALID_PASSWROD_NICKNAME(HttpStatus.BAD_REQUEST,"패스워드에 nickname과 같은값이 있습니다."),
    INVALID_PASSWORD_CONFIRM(HttpStatus.BAD_REQUEST,"패스워드 확인이 패스워드랑 같지 않습니다."),
    INVALID_NICKNAME(HttpStatus.BAD_REQUEST,"중복된 nickname 입니다."),
    INVALID_PASSWORD_CHECK(HttpStatus.BAD_REQUEST,"비밀번호가 일치하지 않습니다."),

    INVALID_USER(HttpStatus.NOT_FOUND,"일치하는 유저가 없습니다.");



    private final HttpStatus httpStatus;
    private final String message;


}
