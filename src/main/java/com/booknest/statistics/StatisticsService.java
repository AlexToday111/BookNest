package com.booknest.statistics;

import com.booknest.progress.ReadingProgress;
import com.booknest.progress.ReadingProgressRepository;
import com.booknest.progress.ReadingStatus;
import com.booknest.session.ReadingSession;
import com.booknest.session.ReadingSessionRepository;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    private final ReadingProgressRepository progressRepository;
    private final ReadingSessionRepository sessionRepository;

    public StatisticsService(
            ReadingProgressRepository progressRepository,
            ReadingSessionRepository sessionRepository
    ) {
        this.progressRepository = progressRepository;
        this.sessionRepository = sessionRepository;
    }

    public ReadingStatisticsResponse reading(Long userId) {
        List<ReadingSession> sessions = sessionRepository.findByUserId(userId);
        long totalPages = sessions.stream().mapToLong(ReadingSession::getPagesRead).sum();
        long totalMinutes = sessions.stream().mapToLong(ReadingSession::getMinutesSpent).sum();
        return new ReadingStatisticsResponse(
                userId,
                progressRepository.countByUserIdAndStatus(userId, ReadingStatus.FINISHED),
                totalPages,
                totalMinutes
        );
    }

    public List<GenreStatisticsResponse> genres(Long userId) {
        return progressRepository.findByUserId(userId).stream()
                .collect(Collectors.groupingBy(progress -> progress.getBook().getGenre().getName(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(entry -> new GenreStatisticsResponse(entry.getKey(), entry.getValue()))
                .toList();
    }

    public List<MonthlyReadingStatisticsResponse> monthly(Long userId) {
        return sessionRepository.findByUserId(userId).stream()
                .collect(Collectors.groupingBy(session -> YearMonth.from(session.getSessionDate()), Collectors.toList()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(entry -> new MonthlyReadingStatisticsResponse(
                        entry.getKey().toString(),
                        entry.getValue().stream().mapToLong(ReadingSession::getPagesRead).sum(),
                        entry.getValue().stream().mapToLong(ReadingSession::getMinutesSpent).sum()
                ))
                .toList();
    }
}
