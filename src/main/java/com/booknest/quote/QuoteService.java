package com.booknest.quote;

import com.booknest.book.BookService;
import com.booknest.common.error.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final BookService bookService;

    public QuoteService(QuoteRepository quoteRepository, BookService bookService) {
        this.quoteRepository = quoteRepository;
        this.bookService = bookService;
    }

    public QuoteResponse create(Long bookId, QuoteRequest request) {
        bookService.getEntity(bookId);
        Quote quote = new Quote(request.userId(), bookId, request.page(), request.text(), request.comment(), request.tags());
        return QuoteMapper.toResponse(quoteRepository.save(quote));
    }

    public List<QuoteResponse> findByBook(Long bookId, Long userId, String tag) {
        List<Quote> quotes = tag == null || tag.isBlank()
                ? quoteRepository.findByBookIdAndUserId(bookId, userId)
                : quoteRepository.findByBookIdAndUserIdAndTagsContaining(bookId, userId, tag);
        return quotes.stream().map(QuoteMapper::toResponse).toList();
    }

    public QuoteResponse findById(String id) {
        return quoteRepository.findById(id)
                .map(QuoteMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Quote", id));
    }

    public void delete(String id) {
        if (!quoteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Quote", id);
        }
        quoteRepository.deleteById(id);
    }
}
