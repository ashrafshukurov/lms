package az.lms.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */
@Getter
public class AgeLimitException extends RuntimeException {
    private final HttpStatus status = HttpStatus.BAD_REQUEST;
    public AgeLimitException() {
        super("The age limit is not appropriate");
    }
}
