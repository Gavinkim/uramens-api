package com.uramens.user.presentation.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class UserUpdateRequestDto {

  @NotBlank(message = "이름은 빈 값일 수 없습니다")
  @Length(max = 20, message = "이름은 20자 이내로 입력하세요")
  private String name;

  @NotBlank(message = "비밀번호는 빈 값일 수 없습니다")
  @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}",
      message = "패스워드는 대문자, 소문자, 특수문자가 적어도 하나씩은 있어야 하며 최소 8자리, 최대 20자리까지 가능합니다")
  private String password;


}
