package com.uramens.user.application;

import com.uramens.exception.user.DuplicatedUserException;
import com.uramens.exception.user.NotFoundUserException;
import com.uramens.user.domain.AuthUser;
import com.uramens.user.domain.PasswordEncoder;
import com.uramens.user.domain.User;
import com.uramens.user.domain.UserRepository;
import com.uramens.user.presentation.dto.request.UserCreateRequestDto;
import com.uramens.user.presentation.dto.request.UserUpdateRequestDto;
import com.uramens.utils.VerificationUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User signUp(UserCreateRequestDto userCreateRequestDto) {
        User user = userCreateRequestDto.toEntity();
        validateDuplicatedUser(user);
        user.setEncodedPassword(passwordEncoder.encrypt(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(long id, UserUpdateRequestDto userUpdateRequestDto, AuthUser loginUser) {
        VerificationUtils.verifyBasicPermission(loginUser, id);
        String encPassword = passwordEncoder.encrypt(userUpdateRequestDto.getPassword());
        User user = userRepository.findById(id).orElseThrow(NotFoundUserException::new);
        user.updateUser(userUpdateRequestDto.getName(), encPassword);
        return user;
    }

    @Override
    public User changeRoleToOwner(long id) {
        User user = userRepository.findById(id).orElseThrow(NotFoundUserException::new);
        user.changeRoleToAdmin();
        return user;
    }

    private void validateDuplicatedUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicatedUserException();
        }
    }

    @Override
    public User getUserInfo(Long userId) {
        return userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
    }
}
