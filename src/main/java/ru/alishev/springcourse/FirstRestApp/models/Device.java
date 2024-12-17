package ru.alishev.springcourse.FirstRestApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Device {

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

    @OneToMany(mappedBy = "device")
    private List<Measurements> measurements;

    public Device() {
    }

    public Device(String name, LocalDateTime createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

}
