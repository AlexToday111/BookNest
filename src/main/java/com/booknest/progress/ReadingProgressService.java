package com.booknest.progress;

import com.booknest.book.Book;
import com.booknest.book.BookService;
import com.booknest.common.error.BusinessRuleException;
import com.booknest.common.error.ResourceNotFoundException;
import com.booknest.user.User;
import com.booknest.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReadingProgressService {

    private final ReadingProgressRepository progressRepository;
    private final UserService userService;
    private final BookService bookService;

    public ReadingProgressService(
            ReadingProgressRepository progressRepository,
            UserService userService,
            BookService bookService
    ) {
        this.progressRepository = progressRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    @Transactional
    public ReadingProgressResponse save(Long bookId, ReadingProgressRequest request) {
        Book book = bookService.getEntity(bookId);
        validateCurrentPage(book, request.currentPage());
        User user = userService.getEntity(request.userId());
        ReadingProgress progress = progressRepository.findByUserIdAndBookId(request.userId(), bookId)
                .orElseGet(() -> new ReadingProgress(user, book, request.status(), request.currentPage()));
        progress.update(request.status(), request.currentPage());
        return ReadingProgressMapper.toResponse(progressRepository.save(progress));
    }

    @Transactional(readOnly = true)
    public ReadingProgressResponse find(Long bookId, Long userId) {
        return progressRepository.findByUserIdAndBookId(userId, bookId)
                .map(ReadingProgressMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Reading progress", userId + ":" + bookId));
    }

    private void validateCurrentPage(Book book, Integer currentPage) {
        if (currentPage > book.getPageCount()) {
            throw new BusinessRuleException("Current page cannot exceed book page count");
        }
    }
}
