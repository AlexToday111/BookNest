package com.booknest.author;

public final class AuthorMapper {

    private AuthorMapper() {
    }

    public static AuthorResponse toResponse(Author author) {
        return new AuthorResponse(author.getId(), author.getName(), author.getBio());
    }
}
