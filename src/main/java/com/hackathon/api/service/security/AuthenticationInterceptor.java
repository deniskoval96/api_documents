package com.hackathon.api.service.security;

import com.hackathon.api.service.UserService;
import com.hackathon.api.service.exceptions.NotAllowedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.Objects.isNull;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!"OPTIONS".equals(request.getMethod())) {
            String authorizationToken = request.getHeader("Authorization");
            if (isNull(authorizationToken) || authorizationToken.isEmpty() || !userService.isSuchTokenExist(authorizationToken)) {
                throw new NotAllowedException("You are not authorize for that. Please use 'Authorization' token.");
            }
        }
        return true;
    }

}
