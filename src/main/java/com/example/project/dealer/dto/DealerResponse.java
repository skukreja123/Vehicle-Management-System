package com.example.project.dealer.dto;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

import com.example.project.dealer.domain.SubscriptionType;

@Data
@Builder
public class DealerResponse {

    private UUID id;
    private String name;
    private String email;
    private SubscriptionType subscriptionType;
}