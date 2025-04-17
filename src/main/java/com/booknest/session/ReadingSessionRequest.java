package com.booknest.session;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ReadingSessionRequest(
        @NotNull Long userId,
        @NotNull @Min(1) Integer pagesRead,
        @NotNull @Min(1) Integer minutesSpent,
        @NotNull LocalDate sessionDate,
        String notes
) {
}
