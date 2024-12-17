package ru.alishev.springcourse.FirstRestApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull(message = "Name cannot be null")
    @Size(min = 3,max = 30,message = "The name must be between 3 and 30 characters")
    private String name;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String createdWhi;

    @OneToMany(mappedBy = "sensor")
    private List<Measurements> measurements;

    public Sensor() {
    }

    public Sensor(String name, LocalDateTime createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedWhi() {
        return createdWhi;
    }

    public void setCreatedWhi(String createdWhi) {
        this.createdWhi = createdWhi;
    }

    public List<Measurements> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurements> measurements) {
        this.measurements = measurements;
    }
}
