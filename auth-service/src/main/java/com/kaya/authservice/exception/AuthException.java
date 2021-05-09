package com.kaya.authservice.exception;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

  private final CodeEnum errorCode;

  public AuthException(CodeEnum errorCode) {
    super();
    this.errorCode = errorCode;
  }
}
