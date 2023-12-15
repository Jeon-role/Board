package com.example.board.user;

import com.example.board.global.constant.ErrorCode;
import com.example.board.global.exception.ApiException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void signup(SignupRequestDto signupRequestDto) {

    if(!signupRequestDto.getPasswordConfirm().equals(signupRequestDto.getPassword())){
      throw new ApiException(ErrorCode.INVALID_PASSWORD_CONFIRM);
    }

    if(signupRequestDto.getPassword().contains(signupRequestDto.getNickname())){
      throw new ApiException(ErrorCode. INVALID_PASSWROD_NICKNAME);
    }

    String nickname = signupRequestDto.getNickname();
    String password= passwordEncoder.encode(signupRequestDto.getPassword());

    Optional<User> checkNickname = userRepository.findByNickname(nickname);

    if(checkNickname.isPresent()){
      throw new ApiException(ErrorCode.INVALID_NICKNAME);
    }

    User user = User.builder()
        .nickname(nickname)
        .password(password).build();
    userRepository.save(user);

  }
  @Override
  public void login(LoginRequestDto loginRequestDto) {
    String nickname=loginRequestDto.getNickname();
    String password=loginRequestDto.getPassword();
    User user = userRepository.findByNickname(nickname).orElseThrow(
        () -> new ApiException(ErrorCode. INVALID_USER)
    );

    if(!passwordEncoder.matches(password, user.getPassword())){
      throw new ApiException(ErrorCode.INVALID_PASSWORD_CHECK);
    }

  }

}
