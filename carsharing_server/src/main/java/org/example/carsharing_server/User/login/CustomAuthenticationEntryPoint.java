package org.example.carsharing_server.User.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import java.io.IOException;

public class CustomAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    public CustomAuthenticationEntryPoint(){
        this.setRealmName("carsharing");
    }
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setHeader("WWW-Authenticate", getRealmName());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());

    }


}
