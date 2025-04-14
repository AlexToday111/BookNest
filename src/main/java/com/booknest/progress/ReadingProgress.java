package com.booknest.progress;

import com.booknest.book.Book;
import com.booknest.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;

@Entity
@Table(name = "reading_progress", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "book_id"}))
public class ReadingProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReadingStatus status;

    @Column(nullable = false)
    private Integer currentPage;

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    protected ReadingProgress() {
    }

    public ReadingProgress(User user, Book book, ReadingStatus status, Integer currentPage) {
        this.user = user;
        this.book = book;
        update(status, currentPage);
    }

    public void update(ReadingStatus status, Integer currentPage) {
        this.status = status;
        this.currentPage = currentPage;
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public ReadingStatus getStatus() {
        return status;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
