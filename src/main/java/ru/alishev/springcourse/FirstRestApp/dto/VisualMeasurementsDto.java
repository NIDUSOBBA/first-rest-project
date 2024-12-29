package ru.alishev.springcourse.FirstRestApp.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisualMeasurementsDto {
    private Double value;

    private Boolean raining;

    private String deviceName;

}
