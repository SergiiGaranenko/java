package com.demo.feign.downstream.exception;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.internalServerError;

import com.demo.feign.downstream.model.ErrorRs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GeneralExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorRs> handleInternalServiceException(final RuntimeException ex) {
    log.error(ex.getMessage(), ex);
    final ErrorRs error = new ErrorRs();
    error.setErrorCode(ex.getMessage());
    return badRequest().body(error);
  }
}
