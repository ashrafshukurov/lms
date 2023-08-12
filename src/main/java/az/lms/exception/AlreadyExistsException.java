package az.lms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */
@Getter
@AllArgsConstructor
public class AlreadyExistsException extends RuntimeException {
    private final HttpStatus status = HttpStatus.CONFLICT;
    private final String message;
}
