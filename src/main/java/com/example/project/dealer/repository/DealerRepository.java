package com.example.project.dealer.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project.dealer.domain.Dealer;

public interface DealerRepository extends JpaRepository<Dealer, UUID> {

    Optional<Dealer> findByIdAndTenantId(UUID id, String tenantId);

    @Query("""
                SELECT d.subscriptionType, COUNT(d)
            FROM Dealer d
            GROUP BY d.subscriptionType
                """)
    List<Object[]> countBySubscriptionType();

}
