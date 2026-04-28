package com.example.project.vehicle.controller;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.dealer.domain.SubscriptionType;
import com.example.project.vehicle.domain.Vehicle;
import com.example.project.vehicle.domain.VehicleStatus;
import com.example.project.vehicle.dto.VehicleRequest;
import com.example.project.vehicle.dto.VehicleResponse;
import com.example.project.vehicle.repository.VehicleRepository;
import com.example.project.vehicle.repository.VehicleSpecification;
import com.example.project.vehicle.service.VehicleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService service;
    private final VehicleRepository repo;

    @PostMapping
    public VehicleResponse create(@Valid @RequestBody VehicleRequest request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public VehicleResponse get(@PathVariable UUID id) {
        return service.get(id);
    }

    @GetMapping
    public Object getAll(@RequestParam(required = false) String model,
            @RequestParam(required = false) VehicleStatus status,
            @RequestParam(required = false) BigDecimal priceMin,
            @RequestParam(required = false) BigDecimal priceMax,
            Pageable pageable, @RequestParam(required = false) SubscriptionType subscription) {

        if (subscription != null) {
            return service.getBySubscription(subscription);
        }
        Page<Vehicle> page = repo.findAll(VehicleSpecification.filter(model, status, priceMin, priceMax), pageable);

        return page.map(this::mapToResponse);
    }

    @PatchMapping("/{id}")
    public VehicleResponse update(
            @PathVariable UUID id,
            @RequestBody VehicleRequest request) {
        return service.update(id, request);
    }

    private VehicleResponse mapToResponse(Vehicle v) {
        return VehicleResponse.builder()
                .id(v.getId())
                .dealerId(v.getDealer().getId())
                .model(v.getModel())
                .price(v.getPrice())
                .status(v.getStatus())
                .build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

}
