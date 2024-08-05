package company.tds.urlshortener.controllers;

import company.tds.urlshortener.dto.ShortenUrlRequest;
import company.tds.urlshortener.dto.ShortenUrlResponse;
import company.tds.urlshortener.services.impl.UrlServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlController {

    private final UrlServiceImpl urlService;

    public UrlController(UrlServiceImpl urlService) {
        this.urlService = urlService;
    }

    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenerUrl(@RequestBody ShortenUrlRequest request) {

        var response = urlService.generateShortUrl(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        String originalUrl = urlService.redirectOriginalUrl(shortUrl);
        return ResponseEntity.status(302).location(URI.create(originalUrl)).build();
    }
}
