package com.ems.backend.controller;

import com.ems.backend.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler({ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(ValidationException e) {
        log.error("Validation error", e);
        return ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .reason(e.getMessage())
                .build();
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ErrorResponse handleUnSupportedContentType(HttpMediaTypeNotSupportedException e) {
        log.error("Content Type Not Supported", e);
        return ErrorResponse.builder()
                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .message(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase())
                .reason(e.getMessage())
                .build();
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse handleUnSupportedAcceptType(HttpMediaTypeNotAcceptableException e) {
        log.error("Accept Not Supported", e);
        return ErrorResponse.builder()
                .code(HttpStatus.NOT_ACCEPTABLE.value())
                .message(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase())
                .reason(e.getMessage())
                .build();
    }
}
