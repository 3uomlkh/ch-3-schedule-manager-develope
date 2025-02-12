package com.example.schedulemanagerdevelop.exception.custom;

import com.example.schedulemanagerdevelop.exception.ErrorMessage;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException() {
        super(ErrorMessage.SCHEDULE_NOT_FOUND.getMessage());
    }
}
