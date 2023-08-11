package az.lms.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */
@Getter
public class NotFoundException extends RuntimeException {
    private final HttpStatus status = HttpStatus.NOT_FOUND;

    public NotFoundException(String message) {
        super(message);
    }
}
