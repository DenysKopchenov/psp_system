# PSP Assignment

## Overview

This is a simple **Payment Service Provider (PSP) simulator** built with **Spring Boot**.  
It supports:

- Payment processing via API
- Card validation using **Luhn algorithm**
- Routing to two mock acquirers based on BIN sum
- In-memory transaction persistence
- Docker support

---

## Features

- **API Endpoint**: `/api/payments` (POST) – accepts card details and payment info
- **Transaction Status**: `PENDING`, `APPROVED`, `DENIED`
- **Acquirer Routing**: Based on sum of BIN digits (even → AcquirerA, odd → AcquirerB)
- **Validation**:
    - Card number (Luhn check)
    - Expiry, CVV, currency, amount

## Prerequisites

- Java 17+
- Maven (`./mvnw` wrapper included)
- Docker (for containerized run)

---

## Build and run with docker
Container will run on http://localhost:8080

./scripts/startup.sh

## Test

./scripts/test.sh