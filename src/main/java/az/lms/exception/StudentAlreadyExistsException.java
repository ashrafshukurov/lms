package az.lms.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */
@Getter
public class StudentAlreadyExistsException extends RuntimeException {
    private final HttpStatus status = HttpStatus.CONFLICT;


    public StudentAlreadyExistsException(String message) {
        super(message);
    }
}
