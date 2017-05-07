package com.edcg.config.security.jwt

import com.edcg.model.Account
import com.edcg.model.jaxb.AccessJwtToken
import com.edcg.model.jaxb.JwtToken
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.time.LocalDateTime
import java.time.ZoneId

/**
 * Created by Edgar on 06/05/2017.
 */
@Component
class JwtTokenFactory {
    @Autowired
    private final JwtSettings settings;

    /**
     * Factory method for issuing new JWT Tokens.
     *
     * @param username
     * @param roles
     * @return
     */
    AccessJwtToken createAccessJwtToken(String principal) {

        if (StringUtils.isBlank(principal))
            throw new IllegalArgumentException("Cannot create JWT Token without username")

        def claims = Jwts.claims().setSubject(principal)
        def roles = ["ROLE_USER"]
        def currentTime = LocalDateTime.now()

        claims.put("scopes", roles)

        def token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                .plusMinutes(settings.getTokenExpirationTime())
                .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact()

        return new AccessJwtToken(token, claims)
    }

    JwtToken createRefreshToken(String principal) {

        if (StringUtils.isBlank(principal))
            throw new IllegalArgumentException("Cannot create JWT Token without username")

        def currentTime = LocalDateTime.now()
        def claims = Jwts.claims().setSubject(principal)

        claims.put("scopes", Arrays.asList("REFRESH_TOKEN"))

        def token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                .plusMinutes(settings.getRefreshTokenExpTime())
                .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact()

        return new AccessJwtToken(token, claims)
    }
}
