package com.example.board.user;

import com.example.board.common.StatusDto;
import com.example.board.jwt.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
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

  @PostMapping("/login")
  public ResponseEntity<StatusDto> login(@RequestBody LoginRequestDto loginRequestDto,
      HttpServletResponse res){
    try {
      userService.login(loginRequestDto);
    }catch (IllegalArgumentException e){
      return ResponseEntity.badRequest().body(new StatusDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
    res.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(loginRequestDto.getNickname()));
    return ResponseEntity.ok().body(new StatusDto("로그인 성공", HttpStatus.OK.value()));

  }





}
