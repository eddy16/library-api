package com.edcg.config.security.auth

import com.edcg.model.jaxb.RawAccessJwtToken
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

/**
 * Created by Edgar on 06/05/2017.
 */
class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 2877954820905567501L

    private RawAccessJwtToken rawAccessToken
    private String principal

    JwtAuthenticationToken(RawAccessJwtToken unsafeToken) {
        super(null)
        this.rawAccessToken = unsafeToken
        this.setAuthenticated(false)
    }

    JwtAuthenticationToken(String principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities)
        this.eraseCredentials()
        this.principal = principal
        super.setAuthenticated(true)
    }

    @Override
    void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead")
        }
        super.setAuthenticated(false)
    }

    @Override
    Object getCredentials() {
        return rawAccessToken
    }

    @Override
    Object getPrincipal() {
        return this.principal
    }

    @Override
    void eraseCredentials() {
        super.eraseCredentials()
        this.rawAccessToken = null
    }
}
