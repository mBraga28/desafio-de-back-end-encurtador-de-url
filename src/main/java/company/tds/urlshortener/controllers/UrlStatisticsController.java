package company.tds.urlshortener.controllers;

import company.tds.urlshortener.dto.UrlAllTimeAccessResponse;
import company.tds.urlshortener.dto.UrlAverageAccessResponse;
import company.tds.urlshortener.entities.Url;
import company.tds.urlshortener.entities.UrlStatistics;
import company.tds.urlshortener.exceptions.UrlNotFoundException;
import company.tds.urlshortener.repositories.UrlRepository;
import company.tds.urlshortener.repositories.UrlStatisticsRepository;
import company.tds.urlshortener.services.UrlStatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/url-statistics")
public class UrlStatisticsController {

    private final UrlStatisticsService urlStatisticsService;

    public UrlStatisticsController(UrlStatisticsService urlStatisticsService) {
        this.urlStatisticsService = urlStatisticsService;
    }

    @GetMapping("allTime/{shortUrl}")
    public ResponseEntity<UrlAllTimeAccessResponse> getStatistics(@PathVariable String shortUrl) {

        var allTimeStatistics = urlStatisticsService.getUrlStatisticsOfAllTime(shortUrl);
        return ResponseEntity.ok(allTimeStatistics);
    }

    @GetMapping("average/{shortUrl}/{period}")
    public ResponseEntity<UrlAverageAccessResponse> getStatistics(@PathVariable String shortUrl, @PathVariable Integer period) {
        var averageStatistics = urlStatisticsService.calculateAverageStatistics(shortUrl, period);
        return ResponseEntity.ok(averageStatistics);
    }


}
