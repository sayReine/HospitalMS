package com.example.hospitalms.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HospitalDto {

    @NotBlank(message = "Hospital name is required")
    @Column(name = "name",unique = true,nullable = false)
    private String name;

    @NotNull(message = "Telephone number is required")
    private Integer telephone;

    @NotBlank(message = "Physical address is required")
    private String address;
}