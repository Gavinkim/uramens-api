package com.uramens.user.domain;

import com.uramens.utils.BaseTimeEntity;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.Assert;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  private String name;

  private String email;

  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  public void setEncodedPassword(String password) {
    this.password = password;
  }

  public void updateUser(String name, String password) {
    this.name = name;
    this.password = password;
  }

  public void changeRoleToAdmin() {
    this.role = Role.ADMIN;
  }

  public static User createInstance(String email, String name, String password) {
    Assert.hasText(email, "이메일이 존재하지 않습니다");
    Assert.hasText(name, "이름이 존재하지 않습니다");
    Assert.hasText(password, "비밀번호가 존재하지 않습니다");

    return new User(email, name, password, getUserDefaultRole());
  }

  private static Role getUserDefaultRole() {
    return Role.BASIC;
  }

  private User(String email, String name, String password, Role role) {
    this.email = email;
    this.name = name;
    this.password = password;
    this.role = role;
  }


  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}
