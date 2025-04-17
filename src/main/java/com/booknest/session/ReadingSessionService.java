package com.booknest.session;

import com.booknest.book.BookService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReadingSessionService {

    private final ReadingSessionRepository sessionRepository;
    private final BookService bookService;

    public ReadingSessionService(ReadingSessionRepository sessionRepository, BookService bookService) {
        this.sessionRepository = sessionRepository;
        this.bookService = bookService;
    }

    public ReadingSessionResponse create(Long bookId, ReadingSessionRequest request) {
        bookService.getEntity(bookId);
        ReadingSession session = new ReadingSession(
                request.userId(),
                bookId,
                request.pagesRead(),
                request.minutesSpent(),
                request.sessionDate(),
                request.notes()
        );
        return ReadingSessionMapper.toResponse(sessionRepository.save(session));
    }

    public List<ReadingSessionResponse> findByBook(Long bookId, Long userId) {
        return sessionRepository.findByBookIdAndUserId(bookId, userId).stream()
                .map(ReadingSessionMapper::toResponse)
                .toList();
    }
}
