package com.booknest.note;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String> {

    List<Note> findByBookIdAndUserId(Long bookId, Long userId);

    List<Note> findByBookIdAndUserIdAndMood(Long bookId, Long userId, String mood);

    List<Note> findByBookIdAndUserIdAndTagsContaining(Long bookId, Long userId, String tag);
}
