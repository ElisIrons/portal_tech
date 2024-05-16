package com.portal_tech.portal_tech.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException409 extends RuntimeException {
    public ConflictException409(String message){
        super(message);
    }

    public ConflictException409(String message, Throwable cause){
        super(message, cause);
    }

}
