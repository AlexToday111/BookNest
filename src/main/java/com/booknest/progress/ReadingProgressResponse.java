package com.booknest.progress;

import java.time.LocalDateTime;

public record ReadingProgressResponse(
        Long id,
        Long userId,
        Long bookId,
        ReadingStatus status,
        Integer currentPage,
        LocalDateTime updatedAt
) {
}
