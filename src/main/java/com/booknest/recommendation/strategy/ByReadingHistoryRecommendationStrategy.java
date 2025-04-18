package com.booknest.recommendation.strategy;

import com.booknest.book.BookMapper;
import com.booknest.book.BookRepository;
import com.booknest.book.BookResponse;
import com.booknest.progress.ReadingProgress;
import com.booknest.progress.ReadingProgressRepository;
import com.booknest.recommendation.RecommendationType;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ByReadingHistoryRecommendationStrategy implements RecommendationStrategy {

    private final ReadingProgressRepository progressRepository;
    private final BookRepository bookRepository;

    public ByReadingHistoryRecommendationStrategy(
            ReadingProgressRepository progressRepository,
            BookRepository bookRepository
    ) {
        this.progressRepository = progressRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public RecommendationType getType() {
        return RecommendationType.BY_READING_HISTORY;
    }

    @Override
    public List<BookResponse> recommend(Long userId) {
        List<ReadingProgress> history = progressRepository.findByUserId(userId);
        if (history.isEmpty()) {
            return bookRepository.findTop10ByOrderByCreatedAtDesc().stream().map(BookMapper::toResponse).toList();
        }
        Long favoriteGenre = history.stream()
                .collect(Collectors.groupingBy(progress -> progress.getBook().getGenre().getId(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(history.getFirst().getBook().getGenre().getId());
        List<Long> excluded = history.stream().map(progress -> progress.getBook().getId()).toList();
        return bookRepository.findTop10ByGenreIdAndIdNotInOrderByCreatedAtDesc(favoriteGenre, excluded).stream()
                .sorted(Comparator.comparing(book -> book.getPublishedYear() == null ? 0 : book.getPublishedYear()))
                .map(BookMapper::toResponse)
                .toList();
    }
}
