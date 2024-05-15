package com.portal_tech.portal_tech.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException422 extends RuntimeException{

    public UnprocessableEntityException422(String message){
        super(message);
    }

    public UnprocessableEntityException422(String message, Throwable cause){
        super(message, cause);
    }
}
