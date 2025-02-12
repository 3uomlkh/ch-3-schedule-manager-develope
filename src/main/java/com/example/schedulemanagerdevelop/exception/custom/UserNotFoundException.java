package com.example.schedulemanagerdevelop.exception.custom;

import com.example.schedulemanagerdevelop.exception.ErrorMessage;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super(ErrorMessage.USER_NOT_FOUND.getMessage());
    }
}
