package com.portal_tech.portal_tech.exceptions.handler;

import com.portal_tech.portal_tech.exceptions.ConflictException409;
import com.portal_tech.portal_tech.exceptions.ExceptionHandler500;
import com.portal_tech.portal_tech.exceptions.UnprocessableEntityException422;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class globalExceptionHandler {
    @ExceptionHandler(ExceptionHandler500.class)
    public ResponseEntity<ErrorResponse> handlerCustomExceptionHandler(ExceptionHandler500 exceptionHandler, HttpServletRequest request){
        return response(exceptionHandler.getMessage(),  request, HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }

    @ExceptionHandler(UnprocessableEntityException422.class)
    public ResponseEntity<ErrorResponse> handlerUnprocessableEntityHandler(UnprocessableEntityException422 unprocessableEntityException422, HttpServletRequest request){
        return response(unprocessableEntityException422.getMessage(), request, HttpStatus.UNPROCESSABLE_ENTITY, LocalDateTime.now());
    }

    @ExceptionHandler(ConflictException409.class)
    public ResponseEntity<ErrorResponse> handlerConflictException409(ConflictException409 conflictException409, HttpServletRequest request){
        return response(conflictException409.getMessage(), request, HttpStatus.CONFLICT, LocalDateTime.now());
    }

    private ResponseEntity<ErrorResponse> response(final String message, final HttpServletRequest request, final HttpStatus status, LocalDateTime data){
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(message, data, status.value(),request.getRequestURI()));
    }
}
