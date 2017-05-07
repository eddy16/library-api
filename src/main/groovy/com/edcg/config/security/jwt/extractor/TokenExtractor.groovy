package com.edcg.config.security.jwt.extractor

/**
 * Created by Edgar on 06/05/2017.
 */
interface TokenExtractor {
    String extract(String payload)
}