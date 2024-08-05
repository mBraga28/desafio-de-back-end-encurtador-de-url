package company.tds.urlshortener.entities;

import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.UUID;

@Document(collection = "urls")
public class Url {

    @Id
    private String id;

    private String originalUrl;

    @Indexed(unique = true)
    private String shortUrl;

    @DBRef
    private List<UrlStatistics> statistics;

    private Boolean isActive;

    public Url() {
        this.id = UUID.randomUUID().toString();
    }

    public Url(Boolean isActive, String shortUrl, String originalUrl) {
        this.isActive = isActive;
        this.shortUrl = shortUrl;
        this.originalUrl = originalUrl;
    }

    public Url(String originalUrl, String shortUrl) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
    }

    public Url(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public List<UrlStatistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<UrlStatistics> statistics) {
        this.statistics = statistics;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
