package com.uramens.user.domain;

import com.uramens.user.presentation.dto.request.LoginRequestDto;
import java.util.Optional;

public interface Authentication {

  public String login(LoginRequestDto loginRequestDto);

  public void verifyLogin(String token);

  public Optional<AuthUser> getLoginUser(String token);
}
