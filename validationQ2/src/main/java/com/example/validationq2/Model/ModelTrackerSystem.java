package com.example.validationq2.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ModelTrackerSystem {

    @NotEmpty(message = "Your ID cannot be empty!")
    @Size(min = 3, message = "Your ID must be longer than 2 characters!")
    private String ID;

    @NotEmpty(message = "Your Title cannot be empty!")
    @Size(min = 9, message = "Your Title must be longer than 8 characters!")
    private String title;

    @NotEmpty(message = "Your Description cannot be empty!")
    @Size(min = 16, message = "Your Description must be longer than 15 characters!")
    private String description;

    @NotEmpty(message = "Your Status cannot be empty!")
    @Pattern(regexp = "Not Started|in Progress|Completed",
            message = "Status must be 'Not Started', 'in Progress', or 'Completed' only!")
    private String status;

    @NotEmpty(message = "Your Company Name cannot be empty!")
    @Size(min = 7, message = "Your Company Name must be longer than 6 characters!")
    private String companyName;
}
