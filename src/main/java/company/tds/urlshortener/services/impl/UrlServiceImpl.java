package company.tds.urlshortener.services.impl;

import company.tds.urlshortener.dto.ShortenUrlRequest;
import company.tds.urlshortener.dto.ShortenUrlResponse;
import company.tds.urlshortener.entities.Url;
import company.tds.urlshortener.repositories.UrlRepository;
import company.tds.urlshortener.services.UrlService;
import company.tds.urlshortener.services.UrlStatisticsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

import java.util.stream.Collectors;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    private final UrlStatisticsService urlStatisticsService;

    private static final int SHORT_URL_LENGTH = 10;
    private static final Random RANDOM = new Random();


    @Value("${api.domain-base}")
    private String domainBase;

    public UrlServiceImpl(UrlRepository urlRepository, UrlStatisticsService urlStatisticsService) {
        this.urlRepository = urlRepository;
        this.urlStatisticsService = urlStatisticsService;
    }

    public ShortenUrlResponse generateShortUrl(ShortenUrlRequest request) {

        String encodedUrl = encodeShortUrl(request.originalUrl());
        Url urlToPersist = new Url(request.originalUrl(), encodedUrl);
        System.out.println(" Current Url ID is: ******" + urlToPersist.getId());
        urlRepository.save(urlToPersist);
        String fullShortUrl = domainBase + "/" + encodedUrl;
        return new ShortenUrlResponse(fullShortUrl);
    }

    public String redirectOriginalUrl(String shortUrl) {
        var url = urlRepository.findByShortUrl(shortUrl);
        urlStatisticsService.updateUrlAccessCounter(url);
        return url.getOriginalUrl();
    }

    public static String encodeShortUrl(String originalUrl) {
        return RANDOM.ints(SHORT_URL_LENGTH, 0, 10)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }
}
