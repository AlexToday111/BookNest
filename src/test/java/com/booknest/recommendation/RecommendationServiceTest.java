package com.booknest.recommendation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.booknest.book.BookResponse;
import com.booknest.recommendation.strategy.RecommendationStrategy;
import java.util.List;
import org.junit.jupiter.api.Test;

class RecommendationServiceTest {

    @Test
    void selectsStrategyByType() {
        RecommendationStrategy strategy = new StaticStrategy(RecommendationType.BY_GENRE);
        RecommendationService service = new RecommendationService(List.of(strategy));

        List<BookResponse> result = service.recommend(1L, RecommendationType.BY_GENRE);

        assertThat(result).hasSize(1);
        assertThat(result.getFirst().title()).isEqualTo("Atomic Habits");
    }

    @Test
    void failsForUnsupportedType() {
        RecommendationService service = new RecommendationService(List.of());

        assertThatThrownBy(() -> service.recommend(1L, RecommendationType.BY_AUTHOR))
                .isInstanceOf(UnsupportedRecommendationTypeException.class);
    }

    private record StaticStrategy(RecommendationType getType) implements RecommendationStrategy {

        @Override
        public List<BookResponse> recommend(Long userId) {
            return List.of(new BookResponse(
                    1L,
                    "Atomic Habits",
                    "A practical book about habits.",
                    1L,
                    "James Clear",
                    1L,
                    "Self-development",
                    320,
                    2018
            ));
        }
    }
}
