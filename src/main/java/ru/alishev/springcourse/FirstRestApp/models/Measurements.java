package ru.alishev.springcourse.FirstRestApp.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Measurements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private double value;

    @Column
    private Boolean raining;

    @ManyToOne()
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    @Column
    private LocalDateTime createdAt;

    public Measurements() {
    }

    public Measurements(double value, boolean raining, Sensor sensorId) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
