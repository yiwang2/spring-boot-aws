package com.genesys.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ElevatorExceptionAdvice {

	  @ResponseBody
	  @ExceptionHandler(ElevatorNotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  String userNotFoundHandler(ElevatorNotFoundException ex) {
	    return ex.getMessage();
	  }
}
