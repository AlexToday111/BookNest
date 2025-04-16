package com.booknest.note;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record NoteRequest(
        @NotNull Long userId,
        @Min(1) Integer page,
        @NotBlank String text,
        List<String> tags,
        String mood
) {
}
