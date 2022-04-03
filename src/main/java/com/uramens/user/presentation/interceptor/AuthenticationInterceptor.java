package com.uramens.user.presentation.interceptor;

import com.uramens.user.domain.Authentication;
import com.uramens.user.domain.LoginCheck;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final Authentication authentication;

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {
        if (((HandlerMethod) handler).getMethodAnnotation(LoginCheck.class) == null) {
            return true;
        }
        verifyLogin(request);

        return true;
    }

    private void verifyLogin(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        authentication.verifyLogin(token);
    }
}
