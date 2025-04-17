package com.booknest.session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("reading_sessions")
public class ReadingSession {

    @Id
    private String id;
    private Long userId;
    private Long bookId;
    private Integer pagesRead;
    private Integer minutesSpent;
    private LocalDate sessionDate;
    private String notes;
    private LocalDateTime createdAt = LocalDateTime.now();

    public ReadingSession(
            Long userId,
            Long bookId,
            Integer pagesRead,
            Integer minutesSpent,
            LocalDate sessionDate,
            String notes
    ) {
        this.userId = userId;
        this.bookId = bookId;
        this.pagesRead = pagesRead;
        this.minutesSpent = minutesSpent;
        this.sessionDate = sessionDate;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public Integer getPagesRead() {
        return pagesRead;
    }

    public Integer getMinutesSpent() {
        return minutesSpent;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
