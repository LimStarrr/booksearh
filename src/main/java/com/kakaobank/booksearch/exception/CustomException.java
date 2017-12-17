package com.kakaobank.booksearch.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    private String name;
    private int status;

    private String reasonPhrase;


    public CustomException(HttpStatus httpStatus) {
        this.name = httpStatus.name();
        this.status = httpStatus.value();
        this.reasonPhrase = httpStatus.getReasonPhrase();
    }

    public CustomException(HttpStatus httpStatus, String reasonPhrase) {
        this.name = httpStatus.name();
        this.status = httpStatus.value();
        this.reasonPhrase = reasonPhrase;
    }

    public CustomException(CustomStatus customStatus) {
        this.name = customStatus.name();
        this.status = customStatus.getValue();
        this.reasonPhrase = customStatus.getReasonPhrase();
    }

    public CustomException(CustomStatus customStatus, String reasonPhrase) {
        this.name = customStatus.name();
        this.status = customStatus.getValue();
        this.reasonPhrase = reasonPhrase;
    }
}
