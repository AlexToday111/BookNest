package com.booknest.review;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {

    List<Review> findByBookId(Long bookId);
}
