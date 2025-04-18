package com.booknest.recommendation;

import com.booknest.book.BookResponse;
import com.booknest.recommendation.strategy.RecommendationStrategy;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    private final Map<RecommendationType, RecommendationStrategy> strategies;

    public RecommendationService(List<RecommendationStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(RecommendationStrategy::getType, Function.identity()));
    }

    public List<BookResponse> recommend(Long userId, RecommendationType type) {
        RecommendationStrategy strategy = strategies.get(type);
        if (strategy == null) {
            throw new UnsupportedRecommendationTypeException(type);
        }
        return strategy.recommend(userId);
    }
}
