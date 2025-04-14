package com.booknest.rating;

public record RatingResponse(Long id, Long userId, Long bookId, Integer score) {
}
