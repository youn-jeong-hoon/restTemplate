package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerAdvisor {
  @ExceptionHandler(BindException.class)
  public ResponseEntity<CommonResponse> bindException(BindException e) {
    return new ResponseEntity<>(new CommonResponse(ResultCode.BIND_EXCEPTION),
            HttpStatus.BAD_REQUEST);
  }
}
