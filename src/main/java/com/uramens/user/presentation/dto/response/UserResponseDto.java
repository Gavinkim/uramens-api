package com.uramens.user.presentation.dto.response;

import com.uramens.user.domain.Role;
import com.uramens.user.domain.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final String email;
    private final String name;
    private final String role;
    private final Long userId;

    public static UserResponseDto createInstance(User user) {
        return new UserResponseDto(user.getUserId(),user.getEmail(), user.getName(), user.getRole());
    }

    private UserResponseDto(Long userId,String email, String name, Role role) {
        this.email = email;
        this.name = name;
        this.role = role.getTitle();
        this.userId = userId;
    }
}