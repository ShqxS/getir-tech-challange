package com.kaya.authservice.exception;

import com.kaya.orderservice.enums.CodeEnum;
import lombok.Getter;

@Getter
public class OrderException extends RuntimeException {

  private final CodeEnum errorCode;

  public OrderException(CodeEnum errorCode) {
    super();
    this.errorCode = errorCode;
  }
}
