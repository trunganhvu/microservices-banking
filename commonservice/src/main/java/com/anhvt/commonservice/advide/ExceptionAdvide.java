/**
 * Copyright 2024
 * Name: ExceptionAdvide
 */
package com.anhvt.commonservice.advide;

import com.anhvt.commonservice.common.CommonException;
import com.anhvt.commonservice.common.ErrorMessage;
import com.anhvt.commonservice.common.ValidateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Common exception handler class
 *
 * @author trunganhvu
 * @date 9/3/2024
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdvide {
    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handlerCommonException(CommonException ex) {
        log.error(String.format("Common error: %s %s %s", ex.getCode(), ex.getStatus(), ex.getMessage()));
        return new ResponseEntity<>(
                new ErrorMessage(ex.getCode(), ex.getMessage(), ex.getStatus()),ex.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handlerException(Exception ex) {
        log.error(String.format("Common error: %s %s %s", ex.getClass(), ex.getCause(), ex.getMessage()));
        return new ResponseEntity<>(
                new ErrorMessage("9999", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handlerValidateException(ValidateException ex) {
        log.error(String.format("Validate error: %s %s %s", ex.getClass(), ex.getCause(), ex.getMessage()));
        return new ResponseEntity<>(
                new ErrorMessage("9000", ex.getMessages().toString(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }
}
