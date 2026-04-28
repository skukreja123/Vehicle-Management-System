

# Dealer & Vehicle Inventory System

A **multi-tenant inventory management system** built using **Spring Boot**, following **Clean Architecture** and a **Modular Monolith** design.

---

## 📌 Overview

This system manages:

- Dealers
- Vehicles
- Multi-tenant data isolation
- Subscription-based filtering
- Admin analytics

It ensures **secure tenant isolation**, **scalable design**, and **production-ready APIs**.

---

## 🏗️ Architecture

### 🔹 Modular Monolith

The system is divided into modules:
dealer/
vehicle/
admin/
common/



---

### 🔹 Clean Architecture

- Controllers → handle HTTP requests  
- Services → business logic  
- Repositories → database access  
- DTOs → API contracts  
- Entities → persistence models  

---

## 🧩 Tech Stack

- Java 17  
- Spring Boot  
- Spring Data JPA  
- PostgreSQL  
- Lombok  
- Hibernate  

---

## 🔐 Multi-Tenancy

### Approach: Shared DB + Shared Schema

Each table includes:
tenant_id



---

### How It Works

1. Each request must include:

X-Tenant-Id: tenant_1


2. A Spring `HandlerInterceptor`:
   - Extracts tenant ID  
   - Stores it in `ThreadLocal (TenantContext)`  

3. Tenant is enforced at:
   - Interceptor → validates request  
   - Service → enforces ownership  
   - Repository → filters queries  

---

### 🚨 Security Rules

| Scenario | Response |
|--------|---------|
| Missing tenant | 400 |
| Cross-tenant access | 403 |
| Invalid input | 400 |
| Not found | 404 |

---

## 📊 Data Model

### Dealer

| Field | Type |
|------|------|
| id | UUID |
| tenant_id | String |
| name | String |
| email | String |
| subscriptionType | BASIC / PREMIUM |

---

### Vehicle

| Field | Type |
|------|------|
| id | UUID |
| tenant_id | String |
| dealer_id | UUID |
| model | String |
| price | Decimal |
| status | AVAILABLE / SOLD |


