package com.example.project.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.project.common.tenant.TenantInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor

public class WebConfig implements WebMvcConfigurer {

    private final TenantInterceptor tenantInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(tenantInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns(
            "/auth/**",
            "/login",
            "/actuator/**",
            "/swagger-ui/**",
            "/v3/api-docs/**"
        );
    }
    
}
