package com.uramens.utils;

import com.uramens.exception.user.ForbiddenException;
import com.uramens.user.domain.AuthUser;
import com.uramens.user.domain.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VerificationUtils {

    public static void verifyAdminPermission(AuthUser loginUser) {
        if (loginUser.getRole() != Role.ADMIN) {
            throw new ForbiddenException();
        }
    }

    public static void verifyAdminPermission(AuthUser loginUser, long userId) {
        if ((loginUser.getRole() != Role.ADMIN)
            && (loginUser.getRole() != Role.ADMIN || !loginUser.getId().equals(userId))) {
            throw new ForbiddenException();
        }
    }

    public static void verifyBasicPermission(AuthUser loginUser, long userId) {
        if ((loginUser.getRole() != Role.ADMIN) && !loginUser.getId().equals(userId)) {
            throw new ForbiddenException();
        }
    }
}
