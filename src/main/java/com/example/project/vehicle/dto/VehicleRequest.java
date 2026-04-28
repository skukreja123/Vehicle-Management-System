package com.example.project.vehicle.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

import com.example.project.vehicle.domain.VehicleStatus;

@Data
public class VehicleRequest {

   
    private UUID dealerId;

    private String model;

    @Positive
    private BigDecimal price;

    private VehicleStatus status;
}