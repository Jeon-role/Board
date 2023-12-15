package com.example.board.user;



public interface UserService {
  void signup(SignupRequestDto signupRequestDto);

  void login(LoginRequestDto loginRequestDto);

}
