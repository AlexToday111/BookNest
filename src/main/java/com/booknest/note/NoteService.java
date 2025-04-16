package com.booknest.note;

import com.booknest.book.BookService;
import com.booknest.common.error.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final BookService bookService;

    public NoteService(NoteRepository noteRepository, BookService bookService) {
        this.noteRepository = noteRepository;
        this.bookService = bookService;
    }

    public NoteResponse create(Long bookId, NoteRequest request) {
        bookService.getEntity(bookId);
        Note note = new Note(request.userId(), bookId, request.page(), request.text(), request.tags(), request.mood());
        return NoteMapper.toResponse(noteRepository.save(note));
    }

    public List<NoteResponse> findByBook(Long bookId, Long userId, String tag, String mood) {
        List<Note> notes;
        if (mood != null && !mood.isBlank()) {
            notes = noteRepository.findByBookIdAndUserIdAndMood(bookId, userId, mood);
        } else if (tag != null && !tag.isBlank()) {
            notes = noteRepository.findByBookIdAndUserIdAndTagsContaining(bookId, userId, tag);
        } else {
            notes = noteRepository.findByBookIdAndUserId(bookId, userId);
        }
        return notes.stream().map(NoteMapper::toResponse).toList();
    }

    public NoteResponse findById(String id) {
        return noteRepository.findById(id)
                .map(NoteMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Note", id));
    }

    public void delete(String id) {
        if (!noteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Note", id);
        }
        noteRepository.deleteById(id);
    }
}
