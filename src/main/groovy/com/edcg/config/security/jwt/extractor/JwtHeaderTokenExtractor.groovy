package com.edcg.config.security.jwt.extractor

import org.apache.commons.lang3.StringUtils
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.stereotype.Component

/**
 * Created by Edgar on 06/05/2017.
 */
@Component
class JwtHeaderTokenExtractor implements TokenExtractor{

    private static final String HEADER_PREFIX = "Bearer "

    @Override
    String extract(String header) {
        if (StringUtils.isBlank(header))
            throw new AuthenticationServiceException("Authorization header cannot be blank!")

        if (header.length() < HEADER_PREFIX.length())
            throw new AuthenticationServiceException("Invalid authorization header size.")

        return header.substring(HEADER_PREFIX.length(), header.length())
    }
}