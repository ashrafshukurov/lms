package az.lms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */
@Getter
@AllArgsConstructor
public class NotFoundException extends RuntimeException {
   private final HttpStatus status = HttpStatus.NOT_FOUND;
   private final String message;
}
