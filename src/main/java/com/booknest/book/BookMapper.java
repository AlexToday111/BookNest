package com.booknest.book;

public final class BookMapper {

    private BookMapper() {
    }

    public static BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getDescription(),
                book.getAuthor().getId(),
                book.getAuthor().getName(),
                book.getGenre().getId(),
                book.getGenre().getName(),
                book.getPageCount(),
                book.getPublishedYear()
        );
    }
}
