package company.tds.urlshortener.services;

import company.tds.urlshortener.dto.ShortenUrlRequest;
import company.tds.urlshortener.dto.ShortenUrlResponse;
import company.tds.urlshortener.entities.Url;

public interface UrlService {

    ShortenUrlResponse generateShortUrl(ShortenUrlRequest request);
}
