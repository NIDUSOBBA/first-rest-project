package ru.alishev.springcourse.FirstRestApp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
public class  Measurements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private double value;

    @Column
    private Boolean raining;

    @ManyToOne()
    @JoinColumn(name = "sensor_id")
    private Device device;

    @Column
    private LocalDateTime createdAt;

    public Measurements() {
    }

    public Measurements(double value, boolean raining, Device deviceId) {
        this.value = value;
        this.raining = raining;
        this.device = deviceId;
    }

}
