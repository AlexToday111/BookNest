package com.booknest.progress;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.booknest.author.Author;
import com.booknest.book.Book;
import com.booknest.book.BookService;
import com.booknest.common.error.BusinessRuleException;
import com.booknest.genre.Genre;
import com.booknest.user.UserService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ReadingProgressServiceTest {

    private final ReadingProgressRepository progressRepository = Mockito.mock(ReadingProgressRepository.class);
    private final UserService userService = Mockito.mock(UserService.class);
    private final BookService bookService = Mockito.mock(BookService.class);
    private final ReadingProgressService progressService = new ReadingProgressService(
            progressRepository,
            userService,
            bookService
    );

    @Test
    void rejectsCurrentPageGreaterThanBookPageCount() {
        Book book = new Book(
                "Clean Code",
                "Programming book.",
                new Author("Robert C. Martin", null),
                new Genre("Programming"),
                464,
                2008
        );

        when(bookService.getEntity(10L)).thenReturn(book);
        when(progressRepository.findByUserIdAndBookId(1L, 10L)).thenReturn(Optional.empty());

        ReadingProgressRequest request = new ReadingProgressRequest(1L, ReadingStatus.READING, 500);

        assertThatThrownBy(() -> progressService.save(10L, request))
                .isInstanceOf(BusinessRuleException.class)
                .hasMessageContaining("Current page");
    }
}
