package com.bklsoftwarevn.exception;


public class ForbiddenEx extends RuntimeException {

    private String message;

    public ForbiddenEx(String message) {
        this.message = message;
    }
}
