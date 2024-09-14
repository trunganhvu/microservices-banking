/**
 * Copyright 2024
 * Name: CommonException
 */
package com.anhvt.commonservice.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Common exception class
 *
 * @author trunganhvu
 * @date 9/3/2024
 */
@Data
public class CommonException extends RuntimeException{
    private final String code;
    private final String message;
    private final HttpStatus status;

    public CommonException(String code, String message, HttpStatus status) {
        super(message);
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
