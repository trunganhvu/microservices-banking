/**
 * Copyright 2024
 * Name: ValidateException
 */
package com.anhvt.commonservice.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * Validated exception object
 *
 * @author trunganhvu
 * @date 9/3/2024
 */
@Data
public class ValidateException extends RuntimeException{
    private final String code;
    private final Map<String, String> messages;
    private final HttpStatus status;

    public ValidateException(String code, Map<String, String> messages, HttpStatus status) {
        this.code = code;
        this.messages = messages;
        this.status = status;
    }
}
