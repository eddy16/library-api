package com.edcg.model.jaxb

import com.edcg.exception.JwtExpiredTokenException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureException
import io.jsonwebtoken.UnsupportedJwtException
import org.springframework.security.authentication.BadCredentialsException

/**
 * Created by Edgar on 06/05/2017.
 */
class RawAccessJwtToken implements JwtToken{
    private String token

    RawAccessJwtToken(String token) {
        this.token = token
    }

    /**
     * Parses and validates JWT Token signature.
     *
     * @throws BadCredentialsException
     * @throws JwtExpiredTokenException
     *
     */
    Jws<Claims> parseClaims(String signingKey) {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token)
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            throw new BadCredentialsException("Invalid JWT token: ", ex)
        } catch (ExpiredJwtException expiredEx) {
            throw new JwtExpiredTokenException(this, "JWT Token expired", expiredEx)
        }
    }

    @Override
    String getToken() {
        return token
    }
}
