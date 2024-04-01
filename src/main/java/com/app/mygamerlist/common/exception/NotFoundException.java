package com.app.mygamerlist.common.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {

    public static final String GAME = "Game is not found.";

    public static final String USER = "User is not found.";

    public NotFoundException(String message) {
        super(message);
    }
}
