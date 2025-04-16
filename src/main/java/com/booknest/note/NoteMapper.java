package com.booknest.note;

public final class NoteMapper {

    private NoteMapper() {
    }

    public static NoteResponse toResponse(Note note) {
        return new NoteResponse(
                note.getId(),
                note.getUserId(),
                note.getBookId(),
                note.getPage(),
                note.getText(),
                note.getTags(),
                note.getMood(),
                note.getCreatedAt(),
                note.getUpdatedAt()
        );
    }
}
