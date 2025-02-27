package company.tds.urlshortener.utils.rateLimiter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class Bucket4jRateLimiter implements RateLimiter {

    private final ConcurrentMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Override
    public boolean isRateLimited(String key) {
        Bucket bucket = buckets.computeIfAbsent(key, this::createNewBucket);
        return !bucket.tryConsume(1);
    }

    private Bucket createNewBucket(String key) {
        Bandwidth limit = Bandwidth.classic(2, Refill.greedy(2, Duration.ofMinutes(1)));
        return Bucket.builder().addLimit(limit).build();
    }
}
