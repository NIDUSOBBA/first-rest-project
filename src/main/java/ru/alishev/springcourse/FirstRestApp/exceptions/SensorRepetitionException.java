package ru.alishev.springcourse.FirstRestApp.exceptions;

public class SensorRepetitionException extends RuntimeException{
    public SensorRepetitionException(String message) {
        super(message);
    }
}
