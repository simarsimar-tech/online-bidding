package com.demo.onlinebidding.exception;

public class RejectedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RejectedException() {
        super();
    }

    public RejectedException(String message) {
        super(message);
    }

    public RejectedException(String message, Throwable e) {
        super(message, e);
    }
}
