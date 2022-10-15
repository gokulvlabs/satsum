package com.gokul.satsum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    @Id
    @GeneratedValue
    private int id;
    @NotBlank(message = "Invalid name")
    private String name;
    @PositiveOrZero(message = "Invalid salary")
    private double salary;
    private boolean on_contract;
    @Size(max=3)
    private String currency;
    @NotBlank(message = "Invalid department")
    private String department;
    @NotBlank(message = "Invalid sub department")
    private String sub_department;

}
