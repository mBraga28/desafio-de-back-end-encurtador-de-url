package company.tds.urlshortener.repositories;

import company.tds.urlshortener.entities.UrlStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlStatisticsRepository extends MongoRepository<UrlStatistics, String> {
}
