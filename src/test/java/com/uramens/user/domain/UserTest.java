package com.uramens.user.domain;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.uramens.user.presentation.dto.request.UserCreateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class UserTest {

  @Nested
  @DisplayName("유저 객체 생성")
  class CreateUser {

    String email;
    String name;
    String password;

    @BeforeEach
    void setup() {
      email = "test@email.com";
      name = "testname";
      password = "@Aabcdef";
    }

    @Test
    @DisplayName("정상 케이스")
    void createUser_Normal() {
      // given
      UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto(email, name, password);

      // when
      User user = userCreateRequestDto.toEntity();

      // then
      assertEquals(email, user.getEmail());
      assertEquals(name, user.getName());
      assertEquals(password, user.getPassword());
    }

    @Nested
    @DisplayName("실패 케이스")
    class createUser_FailCases {

      @Nested
      @DisplayName("이메일")
      class userId {
        @Test
        @DisplayName("null")
        void failNullId() {
          // given
          email = null;

          UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto(email, name, password);

          // when
          Exception exception = assertThrows(Exception.class, userCreateRequestDto::toEntity);

          // then
          assertEquals("이메일이 존재하지 않습니다", exception.getMessage());
        }

        @Test
        @DisplayName("빈 문자열")
        void failEmptyEmail() {
          // given
          email = "";

          UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto(email, name, password);

          // when
          Exception exception = assertThrows(Exception.class, userCreateRequestDto::toEntity);

          // then
          assertEquals("이메일이 존재하지 않습니다", exception.getMessage());
        }

        @Test
        @DisplayName("공백 문자열")
        void failBlankEmail() {
          // given
          email = "  ";

          UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto(email, name, password);

          // when
          Exception exception = assertThrows(Exception.class, userCreateRequestDto::toEntity);

          // then
          assertEquals("이메일이 존재하지 않습니다", exception.getMessage());
        }
      }

      @Nested
      @DisplayName("이름")
      class userName {

        @Test
        @DisplayName("null")
        void failNullName() {
          // given
          name = null;

          UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto(email, name, password);

          // when
          Exception exception = assertThrows(Exception.class, userCreateRequestDto::toEntity);

          // then
          assertEquals("이름이 존재하지 않습니다", exception.getMessage());
        }

        @Test
        @DisplayName("빈 문자열")
        void failEmptyName() {
          // given
          name = "";

          UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto(email, name, password);

          // when
          Exception exception = assertThrows(Exception.class, userCreateRequestDto::toEntity);

          // then
          assertEquals("이름이 존재하지 않습니다", exception.getMessage());
        }

        @Test
        @DisplayName("공백 문자열")
        void failBlankName() {
          // given
          name = "  ";

          UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto(email, name, password);

          // when
          Exception exception = assertThrows(Exception.class, userCreateRequestDto::toEntity);

          // then
          assertEquals("이름이 존재하지 않습니다", exception.getMessage());
        }
      }

      @Nested
      @DisplayName("비밀번호")
      class userPassword {

        @Test
        @DisplayName("null")
        void failNullPassword() {
          // given
          password = null;

          UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto(email, name, password);

          // when
          Exception exception = assertThrows(Exception.class, userCreateRequestDto::toEntity);

          // then
          assertEquals("비밀번호가 존재하지 않습니다", exception.getMessage());
        }

        @Test
        @DisplayName("빈 문자열")
        void failEmptyPassword() {
          // given
          password = "";

          UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto(email, name, password);

          // when
          Exception exception = assertThrows(Exception.class, userCreateRequestDto::toEntity);

          // then
          assertEquals("비밀번호가 존재하지 않습니다", exception.getMessage());
        }

        @Test
        @DisplayName("공백 문자열")
        void failBlankPassword() {
          // given
          password = "  ";

          UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto(email, name, password);

          // when
          Exception exception = assertThrows(Exception.class, userCreateRequestDto::toEntity);

          // then
          assertEquals("비밀번호가 존재하지 않습니다", exception.getMessage());
        }
      }
    }
  }
}