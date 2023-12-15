package com.example.board.user;

import static com.example.board.global.constant.ResponseCode.SUCCESS_LOGIN;
import static com.example.board.global.constant.ResponseCode.SUCCESS_SIGNUP;

import com.example.board.global.dto.SuccessResponse;
import com.example.board.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private final UserService userService;
  private final JwtUtil jwtUtil;

  public UserController(UserService userService,JwtUtil jwtUtil) {
    this.userService = userService;
    this.jwtUtil=jwtUtil;
  }

  @PostMapping("/signup")
  public ResponseEntity<SuccessResponse> signup(@Valid @RequestBody SignupRequestDto signupRequestDto){
    userService.signup(signupRequestDto);
    return ResponseEntity.status(SUCCESS_SIGNUP.getHttpStatus()).body(new SuccessResponse(SUCCESS_SIGNUP));

  }

  @PostMapping("/login")
  public ResponseEntity<SuccessResponse> login(@RequestBody LoginRequestDto loginRequestDto,
      HttpServletResponse res){
    userService.login(loginRequestDto);
    res.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(loginRequestDto.getNickname()));
    return ResponseEntity.status(SUCCESS_LOGIN.getHttpStatus()).body(new SuccessResponse(SUCCESS_LOGIN));
  }





}
