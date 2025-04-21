package com.booknest.book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.booknest.author.Author;
import com.booknest.author.AuthorService;
import com.booknest.genre.Genre;
import com.booknest.genre.GenreService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BookServiceTest {

    private final BookRepository bookRepository = Mockito.mock(BookRepository.class);
    private final AuthorService authorService = Mockito.mock(AuthorService.class);
    private final GenreService genreService = Mockito.mock(GenreService.class);
    private final BookService bookService = new BookService(bookRepository, authorService, genreService);

    @Test
    void createsBookWithAuthorAndGenre() {
        Author author = new Author("James Clear", "Author of Atomic Habits.");
        Genre genre = new Genre("Self-development");
        BookCreateRequest request = new BookCreateRequest(
                "Atomic Habits",
                "A practical book about habits.",
                1L,
                2L,
                320,
                2018
        );

        when(authorService.getEntity(1L)).thenReturn(author);
        when(genreService.getEntity(2L)).thenReturn(genre);
        when(bookRepository.save(Mockito.any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BookResponse response = bookService.create(request);

        assertThat(response.title()).isEqualTo("Atomic Habits");
        assertThat(response.authorName()).isEqualTo("James Clear");
        assertThat(response.genreName()).isEqualTo("Self-development");
        verify(bookRepository).save(Mockito.any(Book.class));
    }
}
