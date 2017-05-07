package com.edcg.config.security.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * Created by Edgar on 06/05/2017.
 */
@Configuration
@ConfigurationProperties(prefix = "security.jwt")
class JwtSettings {
    /**
     * {@link com.edcg.model.jaxb.JwtToken} will expire after this time.
     */
    private Integer tokenExpirationTime

    /**
     * Token issuer.
     */
    private String tokenIssuer

    /**
     * Key is used to sign {@link com.edcg.model.jaxb.JwtToken}.
     */
    private String tokenSigningKey

    /**
     * {@link com.edcg.model.jaxb.JwtToken} can be refreshed during this timeframe.
     */
    private Integer refreshTokenExpTime

    Integer getRefreshTokenExpTime() {
        return refreshTokenExpTime
    }

    void setRefreshTokenExpTime(Integer refreshTokenExpTime) {
        this.refreshTokenExpTime = refreshTokenExpTime
    }

    Integer getTokenExpirationTime() {
        return tokenExpirationTime
    }

    void setTokenExpirationTime(Integer tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime
    }

    String getTokenIssuer() {
        return tokenIssuer
    }
    void setTokenIssuer(String tokenIssuer) {
        this.tokenIssuer = tokenIssuer
    }

    String getTokenSigningKey() {
        return tokenSigningKey
    }

    void setTokenSigningKey(String tokenSigningKey) {
        this.tokenSigningKey = tokenSigningKey
    }
}
