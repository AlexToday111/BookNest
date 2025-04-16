package com.booknest.review;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("reviews")
public class Review {

    @Id
    private String id;
    private Long userId;
    private Long bookId;
    private String title;
    private String text;
    private List<String> pros;
    private List<String> cons;
    private boolean spoiler;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Review(
            Long userId,
            Long bookId,
            String title,
            String text,
            List<String> pros,
            List<String> cons,
            boolean spoiler
    ) {
        this.userId = userId;
        this.bookId = bookId;
        this.title = title;
        this.text = text;
        this.pros = pros;
        this.cons = cons;
        this.spoiler = spoiler;
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

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<String> getPros() {
        return pros;
    }

    public List<String> getCons() {
        return cons;
    }

    public boolean isSpoiler() {
        return spoiler;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
