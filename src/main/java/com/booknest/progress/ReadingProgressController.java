package com.booknest.progress;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books/{bookId}/progress")
public class ReadingProgressController {

    private final ReadingProgressService progressService;

    public ReadingProgressController(ReadingProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReadingProgressResponse create(@PathVariable Long bookId, @Valid @RequestBody ReadingProgressRequest request) {
        return progressService.save(bookId, request);
    }

    @PutMapping
    public ReadingProgressResponse update(@PathVariable Long bookId, @Valid @RequestBody ReadingProgressRequest request) {
        return progressService.save(bookId, request);
    }

    @GetMapping
    public ReadingProgressResponse find(@PathVariable Long bookId, @RequestParam Long userId) {
        return progressService.find(bookId, userId);
    }
}
