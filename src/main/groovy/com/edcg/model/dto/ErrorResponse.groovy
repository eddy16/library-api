package com.edcg.model.dto

import org.springframework.http.HttpStatus

/**
 * Created by Edgar on 06/05/2017.
 */
class ErrorResponse {
    // HTTP Response Status Code
    private final HttpStatus status;

    // General Error message
    private final String message;

    // Error code
    private final ErrorCode errorCode;

    private final Date timestamp;

    protected ErrorResponse(final String message, final ErrorCode errorCode, HttpStatus status) {
        this.message = message;
        this.errorCode = errorCode;
        this.status = status;
        this.timestamp = new java.util.Date();
    }

    static ErrorResponse of(final String message, final ErrorCode errorCode, HttpStatus status) {
        return new ErrorResponse(message, errorCode, status);
    }

    Integer getStatus() {
        return status.value();
    }

    String getMessage() {
        return message;
    }

    ErrorCode getErrorCode() {
        return errorCode;
    }

    Date getTimestamp() {
        return timestamp;
    }
}
