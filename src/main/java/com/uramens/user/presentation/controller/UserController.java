package com.uramens.user.presentation.controller;

import com.uramens.user.application.UserService;
import com.uramens.user.domain.AuthUser;
import com.uramens.user.domain.Authentication;
import com.uramens.user.domain.LoginCheck;
import com.uramens.user.domain.RequireLoginUser;
import com.uramens.user.domain.User;
import com.uramens.user.presentation.dto.request.LoginRequestDto;
import com.uramens.user.presentation.dto.request.UserCreateRequestDto;
import com.uramens.user.presentation.dto.request.UserUpdateRequestDto;
import com.uramens.user.presentation.dto.response.JwtResponseDto;
import com.uramens.user.presentation.dto.response.UserResponseDto;
import com.uramens.utils.ApiResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final Authentication authentication;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserResponseDto> signUp(@Valid @RequestBody final UserCreateRequestDto userCreateRequestDto) {
        User user = userService.signUp(userCreateRequestDto);
        return ApiResponse.createSuccess(UserResponseDto.createInstance(user));
    }

    @PostMapping("/login")
    public ApiResponse<JwtResponseDto> login(@Valid @RequestBody final LoginRequestDto loginRequestDto) {
        String token = authentication.login(loginRequestDto);
        return ApiResponse.createSuccess(JwtResponseDto.createInstance(token));
    }

    @LoginCheck
    @PutMapping("/{id}")
    public ApiResponse<UserResponseDto> updateUser(
        @PathVariable("id") final long id,
        @Valid @RequestBody final UserUpdateRequestDto userUpdateRequestDto,
        @RequireLoginUser AuthUser loginUser
    ) {
        User user = userService.updateUser(id, userUpdateRequestDto, loginUser);
        return ApiResponse.createSuccess(UserResponseDto.createInstance(user));
    }

    @LoginCheck
    @GetMapping
    public ApiResponse<UserResponseDto> getUserInfo(@RequireLoginUser AuthUser loginUser) {
        User user = userService.getUserInfo(loginUser.getId());
        return ApiResponse.createSuccess(UserResponseDto.createInstance(user));
    }
}
