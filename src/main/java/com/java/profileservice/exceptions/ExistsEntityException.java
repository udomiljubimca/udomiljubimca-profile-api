package com.java.profileservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExistsEntityException extends RuntimeException{
    public ExistsEntityException(String message) {
        super(message);
    }
}
