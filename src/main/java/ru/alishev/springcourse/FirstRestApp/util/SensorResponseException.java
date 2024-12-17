package ru.alishev.springcourse.FirstRestApp.util;

import java.time.LocalDateTime;

public class SensorResponseException {

    private String message;

    private LocalDateTime timestamp;

    public SensorResponseException(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
