package com.booknest.rating;

import java.util.List;

public record BookRatingsResponse(Long bookId, double averageScore, List<RatingResponse> ratings) {
}
