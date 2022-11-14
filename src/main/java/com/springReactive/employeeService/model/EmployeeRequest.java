package com.springReactive.employeeService.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
public class EmployeeRequest {

    @NotNull(message = "EmployeeMain.id must be present")
    private int employeeId;
    @NotBlank(message = "EmployeeMain.employeeName must not be null")
    private String employeeName;
    @NotBlank(message = "EmployeeMain.employeeCity must not be null")
    private String employeeCity;
    @NotBlank(message = "EmployeeMain.employeePhone must not be null")
    private String employeePhone;
    @NotNull(message = "EmployeeMain.javaExperience must be present")
    private double javaExperience;
    @NotNull(message = "EmployeeMain.springExperience must be present")
    private double springExperience;

    @Override
    public String toString() {
        return "EmployeeRequest{" +
                "employeeId=" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", employeeCity='" + employeeCity + '\'' +
                ", employeePhone='" + employeePhone + '\'' +
                ", javaExperience=" + javaExperience + '\'' +
                ", springExperience=" + springExperience +'\'' +
                '}';
    }
}
