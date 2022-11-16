package com.compass.msclient.configuration.handler;

import com.compass.msclient.exception.InvalidAttributeException;
import com.compass.msclient.exception.NotFoundAttributeException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@org.springframework.web.bind.annotation.ControllerAdvice(basePackages = "com.compass.msclient.controller")
public class ControllerAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidAttributeException.class)
    public ResponseEntity<MessageExceptionHandler> invalidAttributeException(InvalidAttributeException invalidAttributeException) {
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), "There's data in the body that are not correct or not allowed");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(NotFoundAttributeException.class)
    public ResponseEntity<MessageExceptionHandler> notFoundAttributeException(NotFoundAttributeException notFoundAttributeException) {
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "There's data in the request that doesn't match with the database");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
