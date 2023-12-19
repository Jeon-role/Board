package com.example.board.test;


import com.example.board.user.User;

public interface CommonTest {
	String ANOTHER_PREFIX = "another-";
	Long TEST_USER_ID = 1L;
	Long TEST_ANOTHER_USER_ID = 2L;
	String TEST_USER_NAME = "nickname";
	String TEST_USER_PASSWORD = "password";
	User TEST_USER = User.builder()
		.nickname(TEST_USER_NAME)
		.password(TEST_USER_PASSWORD)
		.build();
	User TEST_ANOTHER_USER = User.builder()
		.nickname(ANOTHER_PREFIX + TEST_USER_NAME)
		.password(ANOTHER_PREFIX + TEST_USER_PASSWORD)
		.build();
}
