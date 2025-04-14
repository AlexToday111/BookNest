package com.booknest.rating;

import com.booknest.book.Book;
import com.booknest.book.BookService;
import com.booknest.common.error.ResourceNotFoundException;
import com.booknest.user.User;
import com.booknest.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserService userService;
    private final BookService bookService;

    public RatingService(RatingRepository ratingRepository, UserService userService, BookService bookService) {
        this.ratingRepository = ratingRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    @Transactional
    public RatingResponse rate(Long bookId, RatingRequest request) {
        Book book = bookService.getEntity(bookId);
        User user = userService.getEntity(request.userId());
        Rating rating = ratingRepository.findByUserIdAndBookId(request.userId(), bookId)
                .orElseGet(() -> new Rating(user, book, request.score()));
        rating.updateScore(request.score());
        return RatingMapper.toResponse(ratingRepository.save(rating));
    }

    @Transactional(readOnly = true)
    public RatingResponse findUserRating(Long bookId, Long userId) {
        return ratingRepository.findByUserIdAndBookId(userId, bookId)
                .map(RatingMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Rating", userId + ":" + bookId));
    }

    @Transactional(readOnly = true)
    public BookRatingsResponse findBookRatings(Long bookId) {
        bookService.getEntity(bookId);
        return new BookRatingsResponse(
                bookId,
                ratingRepository.averageScoreByBookId(bookId),
                ratingRepository.findByBookId(bookId).stream().map(RatingMapper::toResponse).toList()
        );
    }
}
