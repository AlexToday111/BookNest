package com.booknest.review;

public final class ReviewMapper {

    private ReviewMapper() {
    }

    public static ReviewResponse toResponse(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getUserId(),
                review.getBookId(),
                review.getTitle(),
                review.getText(),
                review.getPros(),
                review.getCons(),
                review.isSpoiler(),
                review.getCreatedAt()
        );
    }
}
