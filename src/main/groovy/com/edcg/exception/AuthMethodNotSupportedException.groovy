package com.edcg.exception

import org.springframework.security.authentication.AuthenticationServiceException

/**
 * Created by Edgar on 06/05/2017.
 */
class AuthMethodNotSupportedException extends AuthenticationServiceException{
    private static final long serialVersionUID = 3705043083010304496L

    AuthMethodNotSupportedException(String msg) {
        super(msg)
    }
}
