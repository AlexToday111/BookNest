package com.booknest.statistics;

public record ReadingStatisticsResponse(
        Long userId,
        long finishedBooks,
        long totalPagesRead,
        long totalMinutesSpent
) {
}
