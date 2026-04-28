package com.example.project.admin.service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.project.dealer.domain.SubscriptionType;
import com.example.project.dealer.repository.DealerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final DealerRepository dealerRepo;

    public Map<SubscriptionType, Long> countBySubscription()
    {
        List<Object[]> rows = dealerRepo.countBySubscriptionType();

        Map<SubscriptionType, Long> result = new EnumMap<>(SubscriptionType.class);

        for (SubscriptionType type : SubscriptionType.values())
        {
            result.put(type, 0L);
        }

        for (Object[] r : rows)
        {
            SubscriptionType type = (SubscriptionType) r[0];
            Long count = (Long) r[1];
            result.put(type, count);

            return result;
        }
        return result;
    }

}
