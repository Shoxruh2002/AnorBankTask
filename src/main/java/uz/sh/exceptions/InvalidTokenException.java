package uz.sh.exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class InvalidTokenException extends RuntimeException {
    private final HttpStatus status;

    public InvalidTokenException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public InvalidTokenException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
