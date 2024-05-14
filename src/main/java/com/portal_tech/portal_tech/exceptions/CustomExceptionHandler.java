package com.portal_tech.portal_tech.exceptions;

public class CustomExceptionHandler extends RuntimeException{

    public CustomExceptionHandler(String message){
        super(message);
    }

    public CustomExceptionHandler(String message, Throwable cause){
        super(message, cause
        );
    };
}

