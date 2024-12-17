package ru.alishev.springcourse.FirstRestApp.exceptions;

public class SensorNamingException extends RuntimeException {
    public SensorNamingException(String message) {
        super(message);
    }
}
