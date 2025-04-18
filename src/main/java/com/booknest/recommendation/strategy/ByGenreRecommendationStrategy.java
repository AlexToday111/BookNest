package com.booknest.recommendation.strategy;

import com.booknest.book.BookMapper;
import com.booknest.book.BookRepository;
import com.booknest.book.BookResponse;
import com.booknest.progress.ReadingProgress;
import com.booknest.progress.ReadingProgressRepository;
import com.booknest.recommendation.RecommendationType;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ByGenreRecommendationStrategy implements RecommendationStrategy {

    private final ReadingProgressRepository progressRepository;
    private final BookRepository bookRepository;

    public ByGenreRecommendationStrategy(ReadingProgressRepository progressRepository, BookRepository bookRepository) {
        this.progressRepository = progressRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public RecommendationType getType() {
        return RecommendationType.BY_GENRE;
    }

    @Override
    public List<BookResponse> recommend(Long userId) {
        List<ReadingProgress> history = progressRepository.findByUserId(userId);
        if (history.isEmpty()) {
            return bookRepository.findTop10ByOrderByCreatedAtDesc().stream().map(BookMapper::toResponse).toList();
        }
        Long genreId = history.getFirst().getBook().getGenre().getId();
        List<Long> excluded = history.stream().map(progress -> progress.getBook().getId()).toList();
        return bookRepository.findTop10ByGenreIdAndIdNotInOrderByCreatedAtDesc(genreId, excluded).stream()
                .map(BookMapper::toResponse)
                .toList();
    }
}
