package com.example.board.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignupRequestDto {

  @NotBlank
  @Pattern(regexp = "^[a-z0-9]{3,12}$", message = "이름은 3글자 이상 12글자 이하 영소문자, 숫자만 가능합니다")
  private String nickname;

  @NotBlank
  @Pattern(regexp = "^[a-zA-Z0-9]{4,12}$", message = "비밀번호는 4자 이상 12자 이하 영대소문자, 숫자만 가능합니다.")
  private String password;

  @NotBlank
  @Pattern(regexp = "^[a-zA-Z0-9]{4,12}$", message = "비밀번호확인은 4자 이상 12자 이하 영대소문자, 숫자만 가능합니다.")
  private String passwordConfirm;

}
