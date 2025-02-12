package com.example.schedulemanagerdevelop.exception.custom;

import com.example.schedulemanagerdevelop.exception.ErrorMessage;

public class UnauthorizedActionException extends RuntimeException {
    public UnauthorizedActionException() {
        super(ErrorMessage.UNAUTHORIZED_ACTION.getMessage());
    }
}
