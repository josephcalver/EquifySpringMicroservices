package com.josephcalver.dealsservice.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DealNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(DealNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String dealNotFoundHandler(DealNotFoundException ex) {
        return ex.getMessage();
    }

}
