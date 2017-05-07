package com.edcg.config.security.auth

import com.edcg.exception.AuthMethodNotSupportedException
import com.edcg.model.Account
import com.edcg.model.jaxb.LoginRequest
import com.edcg.util.WebUtil
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.lang3.StringUtils
import org.apache.log4j.Logger
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler


import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by Edgar on 04/05/2017.
 */
class LoginProcessingFilter extends AbstractAuthenticationProcessingFilter{


    private final AuthenticationSuccessHandler successHandler
    private final AuthenticationFailureHandler failureHandler
    private final ObjectMapper objectMapper

    LoginProcessingFilter(String defaultProcessUrl, AuthenticationSuccessHandler successHandler,
                          AuthenticationFailureHandler failureHandler, ObjectMapper objectMapper){
        super(defaultProcessUrl)
        this.successHandler = successHandler
        this.failureHandler = failureHandler
        this.objectMapper = objectMapper
    }

    @Override
    Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        if (!HttpMethod.POST.name() == request.getMethod() || !WebUtil.isAjax(request))
            throw new AuthMethodNotSupportedException("Authentication method not supported")

        def login = objectMapper.readValue(request.getReader(), LoginRequest.class)

        if (StringUtils.isBlank(login.getUsername()) || StringUtils.isBlank(login.getPassword()))
            throw new AuthenticationServiceException("Username or Password not provided")

        def token = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword())

        return this.getAuthenticationManager().authenticate(token)
    }

    @Override
    void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        successHandler.onAuthenticationSuccess(request, response, authResult)
    }

    @Override
    void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext()
        failureHandler.onAuthenticationFailure(request, response, failed)
    }
}
