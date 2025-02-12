package com.example.schedulemanagerdevelop.exception.custom;

import com.example.schedulemanagerdevelop.exception.ErrorMessage;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {
        super(ErrorMessage.INCORRECT_PASSWORD.getMessage());
    }
}
