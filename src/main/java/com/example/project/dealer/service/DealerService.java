package com.example.project.dealer.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.project.common.exception.NotFoundException;
import com.example.project.common.tenant.TenantContext;
import com.example.project.dealer.domain.Dealer;
import com.example.project.dealer.dto.DealerRequest;
import com.example.project.dealer.dto.DealerResponse;
import com.example.project.dealer.repository.DealerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DealerService {

    private final DealerRepository dealerRepository;

    public DealerResponse create(DealerRequest request) {
        Dealer dealer = Dealer.builder().id(UUID.randomUUID()).tenantId(TenantContext.getTenantId())
                .name(request.getName()).email(request.getEmail()).subscriptionType(request.getSubscriptionType())
                .build();

        dealerRepository.save(dealer);

        return mapToResponse(dealer);
    }

    public DealerResponse get(UUID id) {

        Dealer dealer = dealerRepository.findByIdAndTenantId(id, TenantContext.getTenantId())
                .orElseThrow(() -> new NotFoundException("Dealer not found"));

        return mapToResponse(dealer);
    }

    public void delete(UUID id) {

        Dealer dealer = dealerRepository.findByIdAndTenantId(id, TenantContext.getTenantId())
                .orElseThrow(() -> new NotFoundException("Dealer not found"));

        dealerRepository.delete(dealer);
    }

    private DealerResponse mapToResponse(Dealer dealer) {
        return DealerResponse.builder()
                .id(dealer.getId())
                .name(dealer.getName())
                .email(dealer.getEmail())
                .subscriptionType(dealer.getSubscriptionType())
                .build();
    }

    public DealerResponse update(UUID id, DealerRequest request) {

        Dealer dealer = dealerRepository.findByIdAndTenantId(id, TenantContext.getTenantId())
                .orElseThrow(() -> new NotFoundException("Dealer not found"));

        if (request.getName() != null) {
            dealer.setName(request.getName());
        }

        if (request.getEmail() != null) {
            dealer.setEmail(request.getEmail());
        }

        if (request.getSubscriptionType() != null) {
            dealer.setSubscriptionType(request.getSubscriptionType());
        }

        dealerRepository.save(dealer);

        return mapToResponse(dealer);
    }

}
