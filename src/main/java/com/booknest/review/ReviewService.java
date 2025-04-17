package com.booknest.review;

import com.booknest.book.BookService;
import com.booknest.common.error.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookService bookService;

    public ReviewService(ReviewRepository reviewRepository, BookService bookService) {
        this.reviewRepository = reviewRepository;
        this.bookService = bookService;
    }

    public ReviewResponse create(Long bookId, ReviewRequest request) {
        bookService.getEntity(bookId);
        Review review = new Review(
                request.userId(),
                bookId,
                request.title(),
                request.text(),
                request.pros(),
                request.cons(),
                request.spoiler()
        );
        return ReviewMapper.toResponse(reviewRepository.save(review));
    }

    public List<ReviewResponse> findByBook(Long bookId) {
        return reviewRepository.findByBookId(bookId).stream().map(ReviewMapper::toResponse).toList();
    }

    public ReviewResponse findById(String id) {
        return reviewRepository.findById(id)
                .map(ReviewMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Review", id));
    }

    public void delete(String id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("Review", id);
        }
        reviewRepository.deleteById(id);
    }
}
