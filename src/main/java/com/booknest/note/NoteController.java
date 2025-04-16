package com.booknest.note;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/api/books/{bookId}/notes")
    @ResponseStatus(HttpStatus.CREATED)
    public NoteResponse create(@PathVariable Long bookId, @Valid @RequestBody NoteRequest request) {
        return noteService.create(bookId, request);
    }

    @GetMapping("/api/books/{bookId}/notes")
    public List<NoteResponse> findByBook(
            @PathVariable Long bookId,
            @RequestParam Long userId,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String mood
    ) {
        return noteService.findByBook(bookId, userId, tag, mood);
    }

    @GetMapping("/api/notes/{noteId}")
    public NoteResponse findById(@PathVariable String noteId) {
        return noteService.findById(noteId);
    }

    @DeleteMapping("/api/notes/{noteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String noteId) {
        noteService.delete(noteId);
    }
}
