package com.booknest.session;

public final class ReadingSessionMapper {

    private ReadingSessionMapper() {
    }

    public static ReadingSessionResponse toResponse(ReadingSession session) {
        return new ReadingSessionResponse(
                session.getId(),
                session.getUserId(),
                session.getBookId(),
                session.getPagesRead(),
                session.getMinutesSpent(),
                session.getSessionDate(),
                session.getNotes(),
                session.getCreatedAt()
        );
    }
}
