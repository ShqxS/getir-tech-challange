package com.kaya.orderservice.exception;

import com.kaya.orderservice.dto.ErrorResponse;
import com.kaya.orderservice.enums.CodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = {OrderException.class})
  protected ResponseEntity<ErrorResponse> handleBookException(OrderException ex) {
    return ResponseEntity.status(ex.getErrorCode().getHttpStatus())
        .body(
            ErrorResponse.builder()
                .code(ex.getErrorCode().getCode())
                .message(ex.getErrorCode().getMessage())
                .build());
  }

  @ExceptionHandler(
      value = {
        NullPointerException.class,
        RuntimeException.class,
        StackOverflowError.class,
        NoSuchFieldException.class
      })
  protected ResponseEntity<ErrorResponse> handleCommonExceptions() {
    return ResponseEntity.status(CodeEnum.INTERNAL_SERVER_ERROR.getHttpStatus())
        .body(
            ErrorResponse.builder()
                .code(CodeEnum.INTERNAL_SERVER_ERROR.getCode())
                .message(CodeEnum.INTERNAL_SERVER_ERROR.getMessage())
                .build());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    return errors;
  }

  @ExceptionHandler(value = {Exception.class})
  protected ResponseEntity<ErrorResponse> handleException(Exception ex) {
    return ResponseEntity.status(CodeEnum.INTERNAL_SERVER_ERROR.getHttpStatus())
        .body(
            ErrorResponse.builder()
                .code(CodeEnum.INTERNAL_SERVER_ERROR.getCode())
                .message(ex.getMessage())
                .build());
  }
}
