package com.kaya.authservice.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  //@ExceptionHandler(value = {OrderException.class})
  //protected ResponseEntity<ErrorResponse> handleBookException(OrderException ex) {
  //  return ResponseEntity.status(ex.getErrorCode().getHttpStatus())
  //      .body(
  //          ErrorResponse.builder()
  //              .code(ex.getErrorCode().getCode())
  //              .message(ex.getErrorCode().getMessage())
  //              .build());
  //}
//
  //@ExceptionHandler(
  //    value = {
  //      NullPointerException.class,
  //      RuntimeException.class,
  //      StackOverflowError.class,
  //      NoSuchFieldException.class
  //    })
  //protected ResponseEntity<ErrorResponse> handleCommonExceptions() {
  //  return ResponseEntity.status(CodeEnum.INTERNAL_SERVER_ERROR.getHttpStatus())
  //      .body(
  //          ErrorResponse.builder()
  //              .code(CodeEnum.INTERNAL_SERVER_ERROR.getCode())
  //              .message(CodeEnum.INTERNAL_SERVER_ERROR.getMessage())
  //              .build());
  //}
//
  //@ExceptionHandler(value = {Exception.class})
  //protected ResponseEntity<ErrorResponse> handleException(Exception ex) {
  //  return ResponseEntity.status(CodeEnum.INTERNAL_SERVER_ERROR.getHttpStatus())
  //      .body(
  //          ErrorResponse.builder()
  //              .code(CodeEnum.INTERNAL_SERVER_ERROR.getCode())
  //              .message(ex.getMessage())
  //              .build());
  //}
}
