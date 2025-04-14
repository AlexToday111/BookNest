package com.booknest.rating;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books/{bookId}")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/rating")
    @ResponseStatus(HttpStatus.CREATED)
    public RatingResponse rate(@PathVariable Long bookId, @Valid @RequestBody RatingRequest request) {
        return ratingService.rate(bookId, request);
    }

    @GetMapping("/rating")
    public RatingResponse findUserRating(@PathVariable Long bookId, @RequestParam Long userId) {
        return ratingService.findUserRating(bookId, userId);
    }

    @GetMapping("/ratings")
    public BookRatingsResponse findBookRatings(@PathVariable Long bookId) {
        return ratingService.findBookRatings(bookId);
    }
}
