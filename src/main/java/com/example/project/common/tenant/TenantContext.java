package com.example.project.common.tenant;

public class TenantContext {

    private static final ThreadLocal<String> TENAT = new ThreadLocal<>();

    public static void setTenantId(String tenantId)
    {
        TENAT.set(tenantId);
    }

     public static String getTenantId() {
        return TENAT.get();
    }

    public static void clear() {
        TENAT.remove();
    }

}
