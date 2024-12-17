package ru.alishev.springcourse.FirstRestApp.dto;

import ru.alishev.springcourse.FirstRestApp.models.Sensor;

public class MeasurementsDTO {
    private Double value;

    private Boolean raining;

    private Sensor sensor;

    public Double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensorId) {
        this.sensor = sensorId;
    }
}
