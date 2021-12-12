package com.hackathon.api.service.exceptions;

public class NotAllowedException extends RuntimeException {

    public NotAllowedException(String msg) {
        super(msg);
    }
}
