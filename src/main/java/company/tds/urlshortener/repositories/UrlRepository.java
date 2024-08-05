package company.tds.urlshortener.repositories;


import company.tds.urlshortener.entities.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UrlRepository extends MongoRepository<Url, String> {

    Url findByShortUrl(String shortUrl);
}
