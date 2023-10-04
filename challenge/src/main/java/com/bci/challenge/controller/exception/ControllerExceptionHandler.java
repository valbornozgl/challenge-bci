package com.bci.challenge.controller.exception;

import com.bci.challenge.controller.UserController;
import com.bci.challenge.exception.EmailException;
import com.bci.challenge.exception.ErrorResponse;
import com.bci.challenge.exception.PasswordException;
import com.bci.challenge.exception.TokenException;
import com.bci.challenge.exception.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@ControllerAdvice
public class ControllerExceptionHandler {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @ExceptionHandler(EmailException.class)
    public ErrorResponse emailHandleException(EmailException exception) {
        ErrorResponse response = new ErrorResponse(exception.getCode(),exception.getMessage());
        logger.error(exception.getMessage());
        return response;
    }

    @ExceptionHandler(PasswordException.class)
    public ErrorResponse passwordHandleException(PasswordException exception) {
        ErrorResponse response = new ErrorResponse(exception.getCode(),exception.getMessage());
        logger.error(exception.getMessage());
        return response;
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ErrorResponse userdHandleException(UserAlreadyExistsException exception) {
        ErrorResponse response = new ErrorResponse(exception.getCode(),exception.getMessage());
        logger.error(exception.getMessage());
        return response;
    }

    @ExceptionHandler(TokenException.class)
    public ErrorResponse tokendHandleException(TokenException exception) {
        ErrorResponse response = new ErrorResponse(exception.getCode(),exception.getMessage());
        logger.error(exception.getMessage());
        return response;
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception exception) {
        ErrorResponse response = new ErrorResponse(500, HttpStatus.INTERNAL_SERVER_ERROR.toString());
        logger.error(exception.getMessage());
        return response;
    }
}