package com.edcg.config.security.auth

import com.edcg.exception.AuthMethodNotSupportedException
import com.edcg.exception.JwtExpiredTokenException
import com.edcg.model.dto.ErrorCode
import com.edcg.model.dto.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by Edgar on 06/05/2017.
 */
@Component
class LoginAwareAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private final ObjectMapper mapper

    @Override
    void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value())
        response.setContentType(MediaType.APPLICATION_JSON_VALUE)

        if (e instanceof BadCredentialsException) {
            mapper.writeValue(response.getWriter(),
                    ErrorResponse.of("Invalid username or password",
                            ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED))
        } else if (e instanceof JwtExpiredTokenException) {
            mapper.writeValue(response.getWriter(),
                    ErrorResponse.of("Token has expired",
                            ErrorCode.JWT_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED))
        } else if (e instanceof AuthMethodNotSupportedException) {
            mapper.writeValue(response.getWriter(),
                    ErrorResponse.of(e.getMessage(),
                            ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED))
        } else if (e instanceof UsernameNotFoundException){
            mapper.writeValue(response.getWriter(),
                    ErrorResponse.of(e.getMessage(),
                            ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED))
        }

        mapper.writeValue(response.getWriter(),
                ErrorResponse.of("Authentication failed",
                        ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED))
    }
}
