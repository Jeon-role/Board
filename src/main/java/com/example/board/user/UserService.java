package com.example.board.user;


import com.example.board.common.StatusDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
  ResponseEntity<StatusDto> signup(SignupRequestDto signupRequestDto);

  void login(LoginRequestDto loginRequestDto);

}
