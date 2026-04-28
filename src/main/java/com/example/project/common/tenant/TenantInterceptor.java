package com.example.project.common.tenant;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.project.common.exception.BadRequestException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TenantInterceptor implements HandlerInterceptor {

    private static final String HEADER =  "X-Tenant-Id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String tenantId = request.getHeader(HEADER);

        if (tenantId == null || tenantId.isEmpty()) {
            throw new BadRequestException("Missing tenant id in request header");
        }

        TenantContext.setTenantId(tenantId);

        return true;
    }

    @Override
     public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
        TenantContext.clear();
     }

}
