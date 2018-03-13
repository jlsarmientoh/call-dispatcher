package com.almundo.callcenter.exception;


public class InvalidArgumentException extends RuntimeException{

    public InvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidArgumentException(String message) {
        super(message);
    }
}
