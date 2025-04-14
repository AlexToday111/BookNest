package com.booknest.progress;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingProgressRepository extends JpaRepository<ReadingProgress, Long> {

    @EntityGraph(attributePaths = {"user", "book", "book.author", "book.genre"})
    Optional<ReadingProgress> findByUserIdAndBookId(Long userId, Long bookId);

    @EntityGraph(attributePaths = {"book", "book.author", "book.genre"})
    List<ReadingProgress> findByUserId(Long userId);

    long countByUserIdAndStatus(Long userId, ReadingStatus status);
}
