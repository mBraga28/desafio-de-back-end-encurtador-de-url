package company.tds.urlshortener.services;

import company.tds.urlshortener.dto.UrlAllTimeAccessResponse;
import company.tds.urlshortener.dto.UrlAverageAccessResponse;
import company.tds.urlshortener.entities.Url;

public interface UrlStatisticsService {

    UrlAllTimeAccessResponse getUrlStatisticsOfAllTime(String shortUrl);
    UrlAverageAccessResponse calculateAverageStatistics(String shortUrl, Integer numberOfDays);
    void updateUrlAccessCounter(Url url);
}
