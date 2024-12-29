package ru.alishev.springcourse.FirstRestApp.dto;

import lombok.Getter;
import lombok.Setter;
import ru.alishev.springcourse.FirstRestApp.models.Device;

@Getter
@Setter
public class MeasurementsDto {
    private Double value;

    private Boolean raining;

    private Device device;
}
