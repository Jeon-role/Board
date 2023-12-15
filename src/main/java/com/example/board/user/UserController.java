package com.example.board.user;

import com.example.board.common.StatusDto;
import com.example.board.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
  private final UserService userService;
  private final JwtUtil jwtUtil;

  public UserController(UserService userService,JwtUtil jwtUtil) {
    this.userService = userService;
    this.jwtUtil=jwtUtil;
  }

  @PostMapping("/signup")
  public ResponseEntity<StatusDto> signup(@RequestBody SignupRequestDto signupRequestDto){

    return userService.signup(signupRequestDto);
  }



}
