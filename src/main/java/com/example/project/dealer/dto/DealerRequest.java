package com.example.project.dealer.dto;

import com.example.project.dealer.domain.SubscriptionType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DealerRequest {
      @NotBlank
    private String name;

    @Email
    private String email;

    private SubscriptionType subscriptionType;
}
