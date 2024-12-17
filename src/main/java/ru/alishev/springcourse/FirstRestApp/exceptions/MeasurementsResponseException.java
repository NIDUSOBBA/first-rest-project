package ru.alishev.springcourse.FirstRestApp.exceptions;

import java.time.LocalDateTime;

public class MeasurementsResponseException {

    private String message;

    private LocalDateTime timestamp;

    public MeasurementsResponseException(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
