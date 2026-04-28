package com.example.project.admin.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.admin.service.AdminService;
import com.example.project.common.exception.ForbiddenException;
import com.example.project.dealer.domain.SubscriptionType;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminservice;

    @GetMapping("/dealers/countBySubscription")
    public Map<SubscriptionType, Long> count(@RequestHeader(value = "X-Role", required = false) String role)
    {
        if(!"GLOBAL_ADMIN".equals(role))
        {
            throw new ForbiddenException("Global_Admin role required");
        }

        return adminservice.countBySubscription();
    }

}
