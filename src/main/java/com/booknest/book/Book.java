package com.booknest.book;

import com.booknest.author.Author;
import com.booknest.genre.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Column(nullable = false)
    private Integer pageCount;

    private Integer publishedYear;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    protected Book() {
    }

    public Book(String title, String description, Author author, Genre genre, Integer pageCount, Integer publishedYear) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.genre = genre;
        this.pageCount = pageCount;
        this.publishedYear = publishedYear;
    }

    public void update(String title, String description, Author author, Genre genre, Integer pageCount, Integer publishedYear) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.genre = genre;
        this.pageCount = pageCount;
        this.publishedYear = publishedYear;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
