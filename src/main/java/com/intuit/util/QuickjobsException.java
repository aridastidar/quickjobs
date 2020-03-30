package com.intuit.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class QuickjobsException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public QuickjobsException(String message) {
        super(message);
    }

}