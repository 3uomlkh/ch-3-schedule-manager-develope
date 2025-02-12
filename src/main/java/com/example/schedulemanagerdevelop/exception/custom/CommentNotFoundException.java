package com.example.schedulemanagerdevelop.exception.custom;

import com.example.schedulemanagerdevelop.exception.ErrorMessage;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException() {
        super(ErrorMessage.COMMENTS_NOT_FOUND.getMessage());
    }
}
