package com.booknest.book;

public record BookResponse(
        Long id,
        String title,
        String description,
        Long authorId,
        String authorName,
        Long genreId,
        String genreName,
        Integer pageCount,
        Integer publishedYear
) {
}
