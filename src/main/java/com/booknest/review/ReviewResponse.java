package com.booknest.review;

import java.time.LocalDateTime;
import java.util.List;

public record ReviewResponse(
        String id,
        Long userId,
        Long bookId,
        String title,
        String text,
        List<String> pros,
        List<String> cons,
        boolean spoiler,
        LocalDateTime createdAt
) {
}
