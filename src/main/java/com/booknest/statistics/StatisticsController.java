package com.booknest.statistics;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics/users/{userId}")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/reading")
    public ReadingStatisticsResponse reading(@PathVariable Long userId) {
        return statisticsService.reading(userId);
    }

    @GetMapping("/genres")
    public List<GenreStatisticsResponse> genres(@PathVariable Long userId) {
        return statisticsService.genres(userId);
    }

    @GetMapping("/monthly")
    public List<MonthlyReadingStatisticsResponse> monthly(@PathVariable Long userId) {
        return statisticsService.monthly(userId);
    }
}
