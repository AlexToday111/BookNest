package com.booknest.progress;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ReadingProgressRequest(
        @NotNull Long userId,
        @NotNull ReadingStatus status,
        @NotNull @Min(0) Integer currentPage
) {
}
