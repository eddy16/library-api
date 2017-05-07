package com.edcg.config.security

import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by Edgar on 07/05/2017.
 */
@Component
class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{
    @Override
    void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex)
            throws IOException, ServletException {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized")
    }
}
