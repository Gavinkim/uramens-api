package com.uramens.exception.user;

public class ExpiredTokenException extends RuntimeException {

    public ExpiredTokenException() {
        super("만료된 토큰입니다");
    }
}
