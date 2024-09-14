/**
 * Copyright 2024
 * Name: ErrorMessage
 */
package com.anhvt.commonservice.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Error message object
 *
 * @author trunganhvu
 * @date 9/3/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private String code;
    private String message;
    private HttpStatus status;
}
