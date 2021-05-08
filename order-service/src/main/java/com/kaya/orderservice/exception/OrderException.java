package com.kaya.orderservice.exception;

import lombok.Getter;

@Getter
public class OrderException extends RuntimeException {

  private final CodeEnum errorCode;

  public OrderException(CodeEnum errorCode) {
    super();
    this.errorCode = errorCode;
  }
}
