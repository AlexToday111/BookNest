package com.booknest.rating;

public final class RatingMapper {

    private RatingMapper() {
    }

    public static RatingResponse toResponse(Rating rating) {
        return new RatingResponse(
                rating.getId(),
                rating.getUser().getId(),
                rating.getBook().getId(),
                rating.getScore()
        );
    }
}
