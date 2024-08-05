package company.tds.urlshortener.services.impl;

import company.tds.urlshortener.dto.UrlAllTimeAccessResponse;
import company.tds.urlshortener.dto.UrlAverageAccessResponse;
import company.tds.urlshortener.entities.Url;
import company.tds.urlshortener.entities.UrlStatistics;
import company.tds.urlshortener.repositories.UrlRepository;
import company.tds.urlshortener.repositories.UrlStatisticsRepository;
import company.tds.urlshortener.services.UrlStatisticsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UrlStatisticsServiceImpl implements UrlStatisticsService {

    private final UrlRepository urlRepository;

    private final UrlStatisticsRepository urlStatisticsRepository;

    public UrlStatisticsServiceImpl(UrlRepository urlRepository,
                                   UrlStatisticsRepository urlStatisticsRepository) {
        this.urlRepository = urlRepository;
        this.urlStatisticsRepository = urlStatisticsRepository;
    }

    public UrlAllTimeAccessResponse getUrlStatisticsOfAllTime(String shortUrl){
        var statisticsList = urlRepository.findByShortUrl(shortUrl).getStatistics();
        var allTimeStats = calculateAllTimeStatistics(statisticsList);
        return new UrlAllTimeAccessResponse(allTimeStats);
    }

    public UrlAverageAccessResponse calculateAverageStatistics(String shortUrl, Integer numberOfDays) {
        var statisticsList = urlRepository.findByShortUrl(shortUrl).getStatistics();
        var averageStats = calculateAverageDailyStatistics(statisticsList, numberOfDays);
        return new UrlAverageAccessResponse(averageStats);
    }

    public void updateUrlAccessCounter(Url url){
        UrlStatistics statistics = new UrlStatistics();
        statistics.setAccessDate(LocalDateTime.now());
        statistics.setAccessCount(1);
        urlStatisticsRepository.save(statistics);
    }


    private Long calculateAllTimeStatistics(List<UrlStatistics> statisticsList) {
        return statisticsList.stream()
                .mapToLong(UrlStatistics::getAccessCount)
                .sum();
    }

    private Double calculateAverageDailyStatistics(List<UrlStatistics> statisticsList, Integer numberOfDays) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime startDateTime = currentDateTime.minusDays(numberOfDays);

        List<UrlStatistics> filteredStatistics = statisticsList.stream()
                .filter(statistics -> statistics.getAccessDate().isAfter(startDateTime))
                .toList();

        Long totalDays = startDateTime.toLocalDate().datesUntil(currentDateTime.toLocalDate()).count();
        Integer totalAccesses = filteredStatistics.stream()
                .mapToInt(UrlStatistics::getAccessCount)
                .sum();

        double averageAccesses = (double) totalAccesses / totalDays;

        return Math.round(averageAccesses * 10) / 10.0;
    }
}
