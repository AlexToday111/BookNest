package com.booknest.integration;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

@Disabled("Requires Docker; kept as a PostgreSQL Testcontainers integration scenario.")
class PostgresContainerIntegrationTest {

    @Test
    void verifiesPostgresContainerCanStart() {
        try (PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")) {
            postgres.start();
        }
    }
}
