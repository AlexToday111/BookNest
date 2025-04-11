package com.booknest.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookCreateRequest(
        @NotBlank @Size(max = 255) String title,
        @Size(max = 2000) String description,
        @NotNull Long authorId,
        @NotNull Long genreId,
        @NotNull @Min(1) Integer pageCount,
        @Min(0) Integer publishedYear
) {
}
