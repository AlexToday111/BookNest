package com.booknest.quote;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuoteRepository extends MongoRepository<Quote, String> {

    List<Quote> findByBookIdAndUserId(Long bookId, Long userId);

    List<Quote> findByBookIdAndUserIdAndTagsContaining(Long bookId, Long userId, String tag);
}
