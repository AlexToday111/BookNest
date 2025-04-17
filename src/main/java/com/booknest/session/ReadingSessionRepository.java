package com.booknest.session;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReadingSessionRepository extends MongoRepository<ReadingSession, String> {

    List<ReadingSession> findByBookIdAndUserId(Long bookId, Long userId);

    List<ReadingSession> findByUserId(Long userId);
}
