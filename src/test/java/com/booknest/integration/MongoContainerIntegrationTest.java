package com.booknest.integration;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MongoDBContainer;

@Disabled("Requires Docker; kept as a MongoDB Testcontainers integration scenario.")
class MongoContainerIntegrationTest {

    @Test
    void verifiesMongoContainerCanStart() {
        try (MongoDBContainer mongo = new MongoDBContainer("mongo:7")) {
            mongo.start();
        }
    }
}
