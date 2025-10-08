# Bat-Budget Project Status

## ✅ Completed Work

### Project Setup
- Multi-module Gradle project created with **Java 21** as the language.
- Modules structured as:
    - **auth-service** → handles authentication and JWT generation/validation.
    - **user-service** → manages user data and interactions.
    - **cache-service** → initially integrated with Redis (Lettuce client) for caching user data.
    - **write-behind-service** → designed to persist cached data from Redis into PostgreSQL asynchronously.
    - **common** → shared models, Protobuf schemas, utilities.
    - Infrastructure: Docker Compose set up for Kafka, Redis, Zookeeper, and supporting services.

### Messaging & Eventing
- **Kafka integration** across services for structured inter-service communication.
- **Protobuf** adopted for well-defined, schema-based Kafka message serialization/deserialization.
- Kafka consumers currently log events successfully across services.

### Auth & User Flow
- **AuthService** issues JWT tokens for authenticated clients.
- **UserService** accepts requests (via REST endpoints), produces Kafka messages for user events, and consumes messages.
- Kafka consumers in `user-service` are wired to controllers for functional actions (not just logging).

### Caching & Persistence
- Redis integrated (via **Lettuce**) to act as a write-through cache for user data.
- A **write-behind service** consumes Redis changes and persists them into PostgreSQL asynchronously (planned for durability).
- PostgreSQL service provisioned in Docker Compose but not fully wired into services yet.

---

## 📌 Planned Next Steps

### 1. Replace Redis with PostgreSQL
- **Why**: Simplify system design. Current Redis + Write-Behind Service architecture is overcomplicated for project size. PostgreSQL alone can act as the source of truth and handle caching needs via query optimization and indexing.
- **How**:
    - Remove Redis dependencies from `user-service` and `cache-service`.
    - Refactor `user-service` to persist and query data directly from PostgreSQL.
    - Update `write-behind-service` design: repurpose it (or remove it) since PostgreSQL will directly handle durability.
    - Ensure Kafka consumers and producers adapt accordingly (store directly in DB).
- **Deliverable**: A single **refactor PR** where Redis is fully removed and PostgreSQL becomes the central persistence layer.

### 2. Transaction Service
- **Purpose**: Handle user transactions (e.g., income, expenses).
- **Integration**:
    - REST endpoints in `transaction-service` for CRUD on transactions.
    - Use PostgreSQL for storing transaction data.
    - Produce Kafka events for new transactions (to be consumed by `report-service`).
- **Deliverable**: A working `transaction-service` with DB + Kafka wiring.

### 3. Report Service
- **Purpose**: Aggregate user and transaction data for reporting (e.g., monthly budget, category breakdowns).
- **Integration**:
    - Consume Kafka events for users and transactions.
    - Store processed results in PostgreSQL (report tables).
    - Expose REST endpoints for the frontend to fetch reports.
- **Deliverable**: A
