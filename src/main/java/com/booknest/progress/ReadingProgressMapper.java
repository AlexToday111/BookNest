package com.booknest.progress;

public final class ReadingProgressMapper {

    private ReadingProgressMapper() {
    }

    public static ReadingProgressResponse toResponse(ReadingProgress progress) {
        return new ReadingProgressResponse(
                progress.getId(),
                progress.getUser().getId(),
                progress.getBook().getId(),
                progress.getStatus(),
                progress.getCurrentPage(),
                progress.getUpdatedAt()
        );
    }
}
