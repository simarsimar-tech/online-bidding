package com.demo.onlinebidding.controller;

import com.demo.onlinebidding.exception.InvalidStatusException;
import com.demo.onlinebidding.exception.RejectedException;
import com.demo.onlinebidding.exception.ResourceNotFoundException;
import com.demo.onlinebidding.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> exception(ResourceNotFoundException exception) {
        return new ResponseEntity<>(new ErrorMessage("Item not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RejectedException.class)
    public ResponseEntity<Object> exception(RejectedException exception) {
        return new ResponseEntity<>(new ErrorMessage(exception.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = InvalidStatusException.class)
    public ResponseEntity<Object> exception(InvalidStatusException exception) {
        return new ResponseEntity<>(new ErrorMessage(exception.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }
}
