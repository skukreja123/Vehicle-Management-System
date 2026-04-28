package com.example.project.vehicle.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.project.dealer.domain.SubscriptionType;
import com.example.project.vehicle.domain.Vehicle;

public interface VehicleRepository extends
        JpaRepository<Vehicle, UUID>,
        JpaSpecificationExecutor<Vehicle> {

               @Query("""
        SELECT v FROM Vehicle v
        JOIN v.dealer d
        WHERE v.tenantId = :tenantId
        AND d.subscriptionType = :subscription
    """)
        List<Vehicle> findBySubscription(
            @Param("tenantId") String tenantId,
            @Param("subscription") SubscriptionType subscription
    );
}