package com.school.handler;


import com.school.exception.ItemAlreadyInUseException;
import com.school.exception.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.school.handler.ExceptionConstant.ALREADY_IN_USE_MSG;
import static com.school.handler.ExceptionConstant.NOT_FOUND_MSG;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(ItemAlreadyInUseException.class)
    private ResponseEntity<String> itemAlreadyInUseException(ItemAlreadyInUseException e) {
        String message = String.format(ALREADY_IN_USE_MSG, e.getResponse(), e.getResponse());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    private ResponseEntity<String> ItemNotFoundException(ItemNotFoundException e) {
        String message = String.format(NOT_FOUND_MSG, e.getParentItem(), e.getChildItem());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
