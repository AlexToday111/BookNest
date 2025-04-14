package com.booknest.rating;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    @EntityGraph(attributePaths = {"user", "book"})
    Optional<Rating> findByUserIdAndBookId(Long userId, Long bookId);

    @EntityGraph(attributePaths = {"user", "book"})
    List<Rating> findByBookId(Long bookId);

    @Query("select coalesce(avg(r.score), 0) from Rating r where r.book.id = :bookId")
    double averageScoreByBookId(Long bookId);
}
