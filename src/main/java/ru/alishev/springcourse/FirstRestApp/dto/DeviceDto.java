package ru.alishev.springcourse.FirstRestApp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto {

    @NotNull(message = "Name cannot be null")
    @Size(min = 3,max = 30,message = "The name must be between 3 and 30 characters")
    private String name;

}
