package com.example.validationq3.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ModelEventSystem {

    @NotEmpty(message = "Your ID is empty!")
    @Size(min = 3, message = "ID must be more than 2 characters")
    private String ID;

    @NotEmpty(message = "Your description is empty!")
    @Size(min = 16, message = "Description must be more than 15 characters")
    private String description;

    @NotNull(message = "Your capacity is empty!")
    @Min(value = 26, message = "Capacity must be more than 25")
    private int capacity;

    @NotNull(message = "Your startDate is empty!")
    @PastOrPresent(message = "StartDate must be in the past or present")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime startDate;

    @NotNull(message = "Your endDate cannot be null")
    @FutureOrPresent(message = "EndDate must be in the present or future")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime endDate;
}
