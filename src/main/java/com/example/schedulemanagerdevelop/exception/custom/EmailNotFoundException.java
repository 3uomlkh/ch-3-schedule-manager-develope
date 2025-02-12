package com.example.schedulemanagerdevelop.exception.custom;

import com.example.schedulemanagerdevelop.exception.ErrorMessage;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException() {
        super(ErrorMessage.EMAIL_NOT_FOUND.getMessage());
    }
}
