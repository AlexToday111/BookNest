package com.booknest.genre;

public final class GenreMapper {

    private GenreMapper() {
    }

    public static GenreResponse toResponse(Genre genre) {
        return new GenreResponse(genre.getId(), genre.getName());
    }
}
