package com.uramens.user.infrastructure;


import com.uramens.user.domain.PasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncoder implements PasswordEncoder {

  @Override
  public String encrypt(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  @Override
  public boolean isMatch(String password, String hashedPassword) {
    return BCrypt.checkpw(password, hashedPassword);
  }
}
