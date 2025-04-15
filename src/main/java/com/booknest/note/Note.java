package com.booknest.note;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("notes")
public class Note {

    @Id
    private String id;
    private Long userId;
    private Long bookId;
    private Integer page;
    private String text;
    private List<String> tags;
    private String mood;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Note(Long userId, Long bookId, Integer page, String text, List<String> tags, String mood) {
        this.userId = userId;
        this.bookId = bookId;
        this.page = page;
        this.text = text;
        this.tags = tags;
        this.mood = mood;
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

    public Integer getPage() {
        return page;
    }

    public String getText() {
        return text;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getMood() {
        return mood;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
