package com.edcg.exception

import com.edcg.model.jaxb.JwtToken
import org.springframework.security.core.AuthenticationException

/**
 * Created by Edgar on 06/05/2017.
 */
class JwtExpiredTokenException extends AuthenticationException {
    private static final long serialVersionUID = -5959543783324224864L

    private JwtToken token

    JwtExpiredTokenException(String msg) {
        super(msg)
    }

    JwtExpiredTokenException(JwtToken token, String msg, Throwable t) {
        super(msg, t)
        this.token = token
    }

    String token() {
        return this.token.getToken()
    }
}
