package com.genesys.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BuildingExceptionAdvice {

	@ResponseBody
	  @ExceptionHandler(BuildingNotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  String userNotFoundHandler(BuildingNotFoundException ex) {
	    return ex.getMessage();
	  }
}
