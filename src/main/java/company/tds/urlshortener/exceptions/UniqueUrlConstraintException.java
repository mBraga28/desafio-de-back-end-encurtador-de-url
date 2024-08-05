package company.tds.urlshortener.exceptions;

public class UniqueUrlConstraintException extends RuntimeException {
    public UniqueUrlConstraintException(String message) {
        super(message);
    }
}
