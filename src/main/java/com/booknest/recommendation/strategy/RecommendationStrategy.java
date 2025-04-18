package com.booknest.recommendation.strategy;

import com.booknest.book.BookResponse;
import com.booknest.recommendation.RecommendationType;
import java.util.List;

public interface RecommendationStrategy {

    RecommendationType getType();

    List<BookResponse> recommend(Long userId);
}
