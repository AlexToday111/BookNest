package com.booknest.book;

import com.booknest.author.Author;
import com.booknest.author.AuthorService;
import com.booknest.common.error.ResourceNotFoundException;
import com.booknest.genre.Genre;
import com.booknest.genre.GenreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookService(BookRepository bookRepository, AuthorService authorService, GenreService genreService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Transactional
    public BookResponse create(BookCreateRequest request) {
        Author author = authorService.getEntity(request.authorId());
        Genre genre = genreService.getEntity(request.genreId());
        Book book = new Book(
                request.title(),
                request.description(),
                author,
                genre,
                request.pageCount(),
                request.publishedYear()
        );
        return BookMapper.toResponse(bookRepository.save(book));
    }

    @Transactional(readOnly = true)
    public Page<BookResponse> findAll(Pageable pageable) {
        return bookRepository.search(null, null, null, pageable).map(BookMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<BookResponse> search(String title, String author, String genre, Pageable pageable) {
        return bookRepository.search(blankToNull(title), blankToNull(author), blankToNull(genre), pageable)
                .map(BookMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Book getEntity(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", id));
    }

    @Transactional(readOnly = true)
    public BookResponse findById(Long id) {
        return BookMapper.toResponse(getEntity(id));
    }

    @Transactional
    public BookResponse update(Long id, BookUpdateRequest request) {
        Book book = getEntity(id);
        book.update(
                request.title(),
                request.description(),
                authorService.getEntity(request.authorId()),
                genreService.getEntity(request.genreId()),
                request.pageCount(),
                request.publishedYear()
        );
        return BookMapper.toResponse(book);
    }

    @Transactional
    public void delete(Long id) {
        Book book = getEntity(id);
        bookRepository.delete(book);
    }

    private String blankToNull(String value) {
        return value == null || value.isBlank() ? null : value;
    }
}
