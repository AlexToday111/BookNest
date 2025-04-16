package com.booknest.note;

import java.time.LocalDateTime;
import java.util.List;

public record NoteResponse(
        String id,
        Long userId,
        Long bookId,
        Integer page,
        String text,
        List<String> tags,
        String mood,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
