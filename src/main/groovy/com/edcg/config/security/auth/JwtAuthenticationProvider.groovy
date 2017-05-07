package com.edcg.config.security.auth

import com.edcg.config.security.jwt.JwtSettings
import com.edcg.model.jaxb.RawAccessJwtToken
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component

import java.util.stream.Collectors

/**
 * Created by Edgar on 06/05/2017.
 */
@Component
class JwtAuthenticationProvider implements AuthenticationProvider{

    private final JwtSettings jwtSettings

    @Autowired
    JwtAuthenticationProvider(JwtSettings jwtSettings) {
        this.jwtSettings = jwtSettings
    }

    @Override
    Authentication authenticate(Authentication authentication) throws AuthenticationException {

        def rawAccessToken = (RawAccessJwtToken) authentication.getCredentials()
        def jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey())
        def subject = jwsClaims.getBody().getSubject()
        def scopes = jwsClaims.getBody().get("scopes", List.class)
        def authorities = scopes.stream()
                .map({it -> new SimpleGrantedAuthority(it)})
                .collect(Collectors.toList())

        return new JwtAuthenticationToken(subject, authorities)
    }

    @Override
    boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication))
    }
}
