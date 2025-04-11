package com.booknest.genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GenreCreateRequest(@NotBlank @Size(max = 80) String name) {
}
