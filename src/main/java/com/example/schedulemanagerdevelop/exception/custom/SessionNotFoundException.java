package com.example.schedulemanagerdevelop.exception.custom;

import com.example.schedulemanagerdevelop.exception.ErrorMessage;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException() {
        super(ErrorMessage.SESSION_NOT_FOUND.getMessage());
    }
}
