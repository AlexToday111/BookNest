package com.booknest.quote;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping("/api/books/{bookId}/quotes")
    @ResponseStatus(HttpStatus.CREATED)
    public QuoteResponse create(@PathVariable Long bookId, @Valid @RequestBody QuoteRequest request) {
        return quoteService.create(bookId, request);
    }

    @GetMapping("/api/books/{bookId}/quotes")
    public List<QuoteResponse> findByBook(
            @PathVariable Long bookId,
            @RequestParam Long userId,
            @RequestParam(required = false) String tag
    ) {
        return quoteService.findByBook(bookId, userId, tag);
    }

    @GetMapping("/api/quotes/{quoteId}")
    public QuoteResponse findById(@PathVariable String quoteId) {
        return quoteService.findById(quoteId);
    }

    @DeleteMapping("/api/quotes/{quoteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String quoteId) {
        quoteService.delete(quoteId);
    }
}
