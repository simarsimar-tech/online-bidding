package com.demo.onlinebidding.controller;

import com.demo.onlinebidding.exception.RejectedException;
import com.demo.onlinebidding.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> exception(ResourceNotFoundException exception) {
        return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RejectedException.class)
    public ResponseEntity<Object> exception(RejectedException exception) {
        return new ResponseEntity<>("Bidding Rejected", HttpStatus.NOT_ACCEPTABLE);
    }
}
