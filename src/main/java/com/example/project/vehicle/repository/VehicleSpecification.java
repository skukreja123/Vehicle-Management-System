package com.example.project.vehicle.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.example.project.common.tenant.TenantContext;
import com.example.project.vehicle.domain.Vehicle;
import com.example.project.vehicle.domain.VehicleStatus;

public class VehicleSpecification {

    public static Specification<Vehicle> filter( String model,
            VehicleStatus status,
            BigDecimal min,
            BigDecimal max)
            {
 return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            // tenant filter (MANDATORY)
            predicates.add(cb.equal(root.get("tenantId"), TenantContext.getTenantId()));

            if (model != null) {
                predicates.add(cb.like(root.get("model"), "%" + model + "%"));
            }

            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }

            if (min != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), min));
            }

            if (max != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), max));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
            }

}
