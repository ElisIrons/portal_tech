package com.portal_tech.portal_tech.exceptions.handler;

import com.portal_tech.portal_tech.exceptions.CustomExceptionHandler;
import com.portal_tech.portal_tech.exceptions.UnprocessableEntityException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class globalExceptionHandler {
    @ExceptionHandler(CustomExceptionHandler.class)
    public ResponseEntity<ErrorResponse> handlerCustomExceptionHandler(CustomExceptionHandler exceptionHandler, HttpServletRequest request){
        return response(exceptionHandler.getMessage(),  request, HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ErrorResponse> handlerUnprocessableEntityHandler(UnprocessableEntityException unprocessableEntityException, HttpServletRequest request){
        return response(unprocessableEntityException.getMessage(), request, HttpStatus.UNPROCESSABLE_ENTITY, LocalDateTime.now());
    }

    private ResponseEntity<ErrorResponse> response(final String message, final HttpServletRequest request, final HttpStatus status, LocalDateTime data){
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(message, data, status.value(),request.getRequestURI()));
    }
}
