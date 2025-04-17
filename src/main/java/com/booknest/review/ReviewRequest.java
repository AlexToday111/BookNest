package com.booknest.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record ReviewRequest(
        @NotNull Long userId,
        @NotBlank String title,
        @NotBlank String text,
        List<String> pros,
        List<String> cons,
        boolean spoiler
) {
}
