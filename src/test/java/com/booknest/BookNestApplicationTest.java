package com.booknest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BookNestApplicationTest {

    @Test
    void applicationClassExists() {
        assertThat(BookNestApplication.class).isNotNull();
    }
}
