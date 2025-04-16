package com.booknest.quote;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record QuoteRequest(
        @NotNull Long userId,
        @Min(1) Integer page,
        @NotBlank String text,
        String comment,
        List<String> tags
) {
}
