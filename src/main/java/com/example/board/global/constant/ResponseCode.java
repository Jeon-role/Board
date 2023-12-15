package com.example.board.global.constant;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode {

	SUCCESS_SIGNUP(CREATED, "회원가입이 성공하였습니다,"),
	SUCCESS_LOGIN(OK, "로그인이 성공하였습니다,"),
	SUCCESS_BOARD(CREATED,"게시판 생성 성공");


	private final HttpStatus httpStatus;
	private final String message;
}
