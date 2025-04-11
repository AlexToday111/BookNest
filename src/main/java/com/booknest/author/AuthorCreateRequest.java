package com.booknest.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthorCreateRequest(
        @NotBlank @Size(max = 160) String name,
        @Size(max = 4000) String bio
) {
}
