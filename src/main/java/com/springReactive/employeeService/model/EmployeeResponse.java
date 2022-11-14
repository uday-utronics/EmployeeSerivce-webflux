package com.springReactive.employeeService.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeResponse {
    private int employeeId;
    private String employeeName;
    private String employeeCity;
    private String employeePhone;

    private double javaExperience;

    private double springExperience;

    private String status;

}
