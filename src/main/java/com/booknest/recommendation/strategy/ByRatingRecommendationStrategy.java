package com.booknest.recommendation.strategy;

import com.booknest.book.BookMapper;
import com.booknest.book.BookRepository;
import com.booknest.book.BookResponse;
import com.booknest.recommendation.RecommendationType;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class ByRatingRecommendationStrategy implements RecommendationStrategy {

    private final BookRepository bookRepository;

    public ByRatingRecommendationStrategy(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public RecommendationType getType() {
        return RecommendationType.BY_RATING;
    }

    @Override
    public List<BookResponse> recommend(Long userId) {
        return bookRepository.findTopRated(PageRequest.of(0, 10)).stream()
                .map(BookMapper::toResponse)
                .toList();
    }
}
