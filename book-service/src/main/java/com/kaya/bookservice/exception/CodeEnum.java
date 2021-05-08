package com.kaya.bookservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CodeEnum {
  INTERNAL_SERVER_ERROR(1, "Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR),
  FIELD_VALIDATION_ERROR(2, "Field validation error.", HttpStatus.BAD_REQUEST),
  CONTENT_NOT_FOUND_ERROR(3, "Content not found.", HttpStatus.BAD_REQUEST),
  SUCCESS_RESPONSE(4, "Content not found.", HttpStatus.OK);

  private final int code;
  private final String message;
  private final HttpStatus httpStatus;
}
