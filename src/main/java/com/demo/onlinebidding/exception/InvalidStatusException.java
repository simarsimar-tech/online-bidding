package com.demo.onlinebidding.exception;

public class InvalidStatusException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidStatusException() {
        super();
    }

    public InvalidStatusException(String message) {
        super(message);
    }

    public InvalidStatusException(String message, Throwable e) {
        super(message, e);
    }
}
