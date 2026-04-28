package com.example.project.vehicle.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.project.common.exception.ForbiddenException;
import com.example.project.common.exception.NotFoundException;
import com.example.project.common.tenant.TenantContext;
import com.example.project.dealer.domain.Dealer;
import com.example.project.dealer.domain.SubscriptionType;
import com.example.project.dealer.repository.DealerRepository;
import com.example.project.vehicle.domain.Vehicle;
import com.example.project.vehicle.dto.VehicleRequest;
import com.example.project.vehicle.dto.VehicleResponse;
import com.example.project.vehicle.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehiclerepo;
    private final DealerRepository dealrepo;

    public VehicleResponse create(VehicleRequest request) {
        String tenantId = TenantContext.getTenantId();

        Dealer dealer = dealrepo.findById(request.getDealerId())
                .orElseThrow(() -> new NotFoundException("Dealer not found"));

        if (!dealer.getTenantId().equals(tenantId)) {
            throw new ForbiddenException("Dealer does not belong to your tenant");
        }

        Vehicle vehicle = Vehicle.builder()
                .id(UUID.randomUUID())
                .tenantId(tenantId)
                .dealer(dealer)
                .model(request.getModel())
                .price(request.getPrice())
                .status(request.getStatus())
                .build();

        vehiclerepo.save(vehicle);
        return mapToResponse(vehicle);
    }

    public VehicleResponse update(UUID id, VehicleRequest request) {

        Vehicle v = vehiclerepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));

        String tenantId = TenantContext.getTenantId();

        if (!v.getTenantId().equals(tenantId)) {
            throw new ForbiddenException("Access denied");
        }

        if (request.getModel() != null) {
            v.setModel(request.getModel());
        }

        if (request.getPrice() != null) {
            v.setPrice(request.getPrice());
        }

        if (request.getStatus() != null) {
            v.setStatus(request.getStatus());
        }

        if (request.getDealerId() != null) {
            var dealer = dealrepo.findById(request.getDealerId())
                    .orElseThrow(() -> new NotFoundException("Dealer not found"));

            if (!dealer.getTenantId().equals(tenantId)) {
                throw new ForbiddenException("Dealer mismatch");
            }

            v.setDealer(dealer);
        }

        vehiclerepo.save(v);

        return mapToResponse(v);
    }

    public VehicleResponse get(UUID id) {

        Vehicle v = vehiclerepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));

        if (!v.getTenantId().equals(TenantContext.getTenantId())) {
            throw new ForbiddenException("Access denied");
        }

        return mapToResponse(v);
    }

    public void delete(UUID id) {

        Vehicle v = vehiclerepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));

        if (!v.getTenantId().equals(TenantContext.getTenantId())) {
            throw new ForbiddenException("Access denied");
        }

        vehiclerepo.delete(v);
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

    public List<VehicleResponse> getBySubscription(SubscriptionType sub) {

        String tenantId = TenantContext.getTenantId();

        return vehiclerepo.findBySubscription(tenantId, sub)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

}
