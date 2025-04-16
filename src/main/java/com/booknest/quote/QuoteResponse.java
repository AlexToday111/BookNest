package com.booknest.quote;

import java.time.LocalDateTime;
import java.util.List;

public record QuoteResponse(
        String id,
        Long userId,
        Long bookId,
        Integer page,
        String text,
        String comment,
        List<String> tags,
        LocalDateTime createdAt
) {
}
