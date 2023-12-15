package com.example.board.user;


import com.example.board.common.StatusDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
  // 닉네임과 같은 값 을 찾는거는 contains 이용
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;


  public ResponseEntity<StatusDto> signup(SignupRequestDto signupRequestDto) {

    if(!signupRequestDto.getPasswordConfirm().equals(signupRequestDto.getPassword())){
      return ResponseEntity.badRequest().body(new StatusDto("패스워드 확인이 패스워드랑 같지 않습니다.", HttpStatus.BAD_REQUEST.value()));
    }

    if(signupRequestDto.getPassword().contains(signupRequestDto.getNickname())){
      return ResponseEntity.badRequest().body(new StatusDto("패스워드에 nickname과 같은값이 있습니다.", HttpStatus.BAD_REQUEST.value()));
    }

    String nickname = signupRequestDto.getNickname();
    String password= passwordEncoder.encode(signupRequestDto.getPassword());

    Optional<User> checkNickname = userRepository.findByNickname(nickname);

    if(checkNickname.isPresent()){
      return ResponseEntity.badRequest().body(new StatusDto("중복된 nickname 입니다.", HttpStatus.BAD_REQUEST.value()));
    }

    User user = User.builder()
        .nickname(nickname)
        .password(password).build();
    userRepository.save(user);

    return ResponseEntity.status(HttpStatus.CREATED.value()).body(new StatusDto("회원가입 성공", HttpStatus.CREATED.value()));

  }

  public void login(LoginRequestDto loginRequestDto) {
    String nickname=loginRequestDto.getNickname();
    String password=loginRequestDto.getPassword();
    User user = userRepository.findByNickname(nickname).orElseThrow(
        () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
    );

    if(!passwordEncoder.matches(password, user.getPassword())){
      throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

  }


}
