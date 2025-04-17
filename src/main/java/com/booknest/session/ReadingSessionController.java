package com.booknest.session;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadingSessionController {

    private final ReadingSessionService sessionService;

    public ReadingSessionController(ReadingSessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/api/books/{bookId}/sessions")
    @ResponseStatus(HttpStatus.CREATED)
    public ReadingSessionResponse create(
            @PathVariable Long bookId,
            @Valid @RequestBody ReadingSessionRequest request
    ) {
        return sessionService.create(bookId, request);
    }

    @GetMapping("/api/books/{bookId}/sessions")
    public List<ReadingSessionResponse> findByBook(@PathVariable Long bookId, @RequestParam Long userId) {
        return sessionService.findByBook(bookId, userId);
    }
}
