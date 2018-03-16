package com.serviceflow.task.muhammadali.fizzbuzz.controller;

import com.serviceflow.task.muhammadali.fizzbuzz.constant.GenericConstants;
import com.serviceflow.task.muhammadali.fizzbuzz.entity.Response;
import com.serviceflow.task.muhammadali.fizzbuzz.exceptions.InvalidRuleIdException;
import com.serviceflow.task.muhammadali.fizzbuzz.exceptions.MalformedJsonException;
import com.serviceflow.task.muhammadali.fizzbuzz.exceptions.TooManyNumbersException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


public class FizzBuzzController {

    //handlers for exceptions

    //Generic
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleDefaultException(Exception e) {
        return new Response(GenericConstants.ERROR.getValue(), "Exception");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleDefaultException(RuntimeException e) {
        return new Response(GenericConstants.ERROR.getValue(), "RuntimeException");
    }


    //expected errors
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)
    public Response handleDefaultException(TooManyNumbersException e) {
        return new Response(GenericConstants.ERROR.getValue(), "Too bad, you choose too many numbers, and this overload our servers, please try again with fewer numbers");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public Response handleDefaultException(MalformedJsonException e) {
        return new Response(GenericConstants.ERROR.getValue(), "The Json parameter is invalid!");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public Response handleDefaultException(InvalidRuleIdException e) {
        return new Response(GenericConstants.ERROR.getValue(), "The Rules Id must be greater than 0!");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public Response handleDefaultException(NumberFormatException e) {
        return new Response(GenericConstants.ERROR.getValue(), "You choose a very large number, please try the 'ClassicLN' version");
    }


}
