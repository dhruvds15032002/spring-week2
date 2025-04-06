package com.example.mvc.learn.week2.dto;

import com.example.mvc.learn.week2.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;

    @NotNull(message = "Required Field in Employee: name")
    private String name;

    private String email;

    private Integer age;

    private LocalDate dateOfJoining;

    @EmployeeRoleValidation
    private String role;

    @JsonProperty("isActive")
    private Boolean isActive;
}
