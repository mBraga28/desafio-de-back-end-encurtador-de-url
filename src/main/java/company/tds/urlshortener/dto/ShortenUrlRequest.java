package company.tds.urlshortener.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ShortenUrlRequest(
        @JsonProperty("original_url")
        String originalUrl){
}
