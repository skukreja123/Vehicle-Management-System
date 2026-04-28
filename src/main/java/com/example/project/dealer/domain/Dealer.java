package com.example.project.dealer.domain;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dealers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dealer {

    @Id
    private UUID id;

    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false, unique = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_type")
    private SubscriptionType subscriptionType;

}
