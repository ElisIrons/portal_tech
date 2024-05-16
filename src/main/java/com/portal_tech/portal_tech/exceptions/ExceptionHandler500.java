package com.portal_tech.portal_tech.exceptions;

public class ExceptionHandler500 extends RuntimeException{

    public ExceptionHandler500(String message){
        super(message);
    }

    public ExceptionHandler500(String message, Throwable cause){
        super(
                message, cause
        );
    };
}

