package com.uramens.user.application;


import com.uramens.user.domain.AuthUser;
import com.uramens.user.domain.User;
import com.uramens.user.presentation.dto.request.UserCreateRequestDto;
import com.uramens.user.presentation.dto.request.UserUpdateRequestDto;

public interface UserService {

    public User signUp(UserCreateRequestDto userCreateRequestDto);

    public User updateUser(long id, UserUpdateRequestDto userUpdateRequestDto, AuthUser loginUser);

    public User changeRoleToOwner(long id);

    User getUserInfo(Long userId);
}
