package com.booknest.session;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReadingSessionResponse(
        String id,
        Long userId,
        Long bookId,
        Integer pagesRead,
        Integer minutesSpent,
        LocalDate sessionDate,
        String notes,
        LocalDateTime createdAt
) {
}
