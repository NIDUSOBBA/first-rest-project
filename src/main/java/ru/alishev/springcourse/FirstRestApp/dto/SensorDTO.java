package ru.alishev.springcourse.FirstRestApp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SensorDTO {

    @NotNull(message = "Name cannot be null")
    @Size(min = 3,max = 30,message = "The name must be between 3 and 30 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
