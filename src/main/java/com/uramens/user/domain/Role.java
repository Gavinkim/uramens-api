package com.uramens.user.domain;

public enum Role {

  ADMIN("관리자"), BASIC("일반 사용자")
  ;

  private final String title;

  Role(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
