package com.kaya.bookservice.exception;

import lombok.Getter;

@Getter
public class BookException extends RuntimeException {

    private final CodeEnum errorCode;

    public BookException(CodeEnum errorCode) {
        super();
        this.errorCode = errorCode;
    }
}
