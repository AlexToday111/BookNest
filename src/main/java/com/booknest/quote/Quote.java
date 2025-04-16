package com.booknest.quote;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("quotes")
public class Quote {

    @Id
    private String id;
    private Long userId;
    private Long bookId;
    private Integer page;
    private String text;
    private String comment;
    private List<String> tags;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Quote(Long userId, Long bookId, Integer page, String text, String comment, List<String> tags) {
        this.userId = userId;
        this.bookId = bookId;
        this.page = page;
        this.text = text;
        this.comment = comment;
        this.tags = tags;
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

    public String getComment() {
        return comment;
    }

    public List<String> getTags() {
        return tags;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
