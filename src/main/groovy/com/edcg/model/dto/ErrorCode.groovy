package com.edcg.model.dto

import com.fasterxml.jackson.annotation.JsonValue

/**
 * Created by Edgar on 06/05/2017.
 */
enum ErrorCode {
    GLOBAL(2),

    AUTHENTICATION(10), JWT_TOKEN_EXPIRED(11)

    private int errorCode

    private ErrorCode(int errorCode) {
        this.errorCode = errorCode
    }

    @JsonValue
    int getErrorCode() {
        return errorCode
    }
}