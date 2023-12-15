package com.example.board.user;


import org.springframework.http.ResponseEntity;

public interface UserService {
  void signup(SignupRequestDto signupRequestDto);

  void login(LoginRequestDto loginRequestDto);

}
