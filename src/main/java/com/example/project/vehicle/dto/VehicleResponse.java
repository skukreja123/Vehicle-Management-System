package com.example.project.vehicle.dto;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

import com.example.project.vehicle.domain.VehicleStatus;

@Data
@Builder
public class VehicleResponse {

    private UUID id;
    private UUID dealerId;
    private String model;
    private BigDecimal price;
    private VehicleStatus status;
}