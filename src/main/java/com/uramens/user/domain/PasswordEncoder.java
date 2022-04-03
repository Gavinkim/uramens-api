package com.uramens.user.domain;

public interface PasswordEncoder {

  public String encrypt(String password);

  public boolean isMatch(String password, String hashedPassword);
}