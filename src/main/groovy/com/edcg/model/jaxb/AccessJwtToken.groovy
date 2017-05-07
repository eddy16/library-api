package com.edcg.model.jaxb

import com.fasterxml.jackson.annotation.JsonIgnore
import io.jsonwebtoken.Claims

/**
 * Created by Edgar on 06/05/2017.
 */
final class AccessJwtToken implements JwtToken{
    private final String rawToken
    @JsonIgnore
    private Claims claims

    protected AccessJwtToken(final String token, Claims claims) {
        this.rawToken = token;
        this.claims = claims;
    }

    @Override
    String getToken() {
        return this.rawToken
    }

    Claims getClaims() {
        return claims;
    }
}
