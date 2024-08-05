package company.tds.urlshortener.utils.rateLimiter;

public interface RateLimiter {
    boolean isRateLimited(String key);
}
