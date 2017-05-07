package com.edcg.config.security.auth

import com.edcg.config.security.jwt.JwtTokenFactory
import com.edcg.model.jaxb.JwtToken
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.web.WebAttributes
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

/**
 * Created by Edgar on 06/05/2017.
 */
@Component
class LoginAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private final ObjectMapper mapper
    @Autowired
    private final JwtTokenFactory tokenFactory

    @Override
    void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        def principal = (String) authentication.getPrincipal()
        def accessToken = tokenFactory.createAccessJwtToken(principal)
        def refreshToken = tokenFactory.createRefreshToken(principal)
        def tokenMap = new HashMap<String, String>()

        tokenMap.put("token", accessToken.getToken())
        tokenMap.put("refreshToken", refreshToken.getToken())
        response.setStatus(HttpStatus.OK.value())
        response.setContentType(MediaType.APPLICATION_JSON_VALUE)

        mapper.writeValue(response.getWriter(), tokenMap)

        clearAuthenticationAttributes(request)
    }

    /**
     * Removes temporary authentication-related data which may have been stored
     * in the session during the authentication process..
     *
     */
    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        def session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

}
