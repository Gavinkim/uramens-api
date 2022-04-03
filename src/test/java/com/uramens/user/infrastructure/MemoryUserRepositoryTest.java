package com.uramens.user.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.uramens.user.domain.User;
import com.uramens.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemoryUserRepositoryTest {

    UserRepository userRepository = new MemoryUserRepository();

    User user;
    String email;
    String name;
    String password;

    @BeforeEach
    public void setUp() {
        email = "test1223@test";
        name = "testname";
        password = "@Aabcdef";
        user = User.createInstance(email, name, password);
    }

    @Test
    @DisplayName("사용자를 저장한다.")
    public void save() {
        // when
        userRepository.save(user);

        // then
        assertTrue(userRepository.existsByEmail(user.getEmail()));

        userRepository.deleteAll();
    }
}