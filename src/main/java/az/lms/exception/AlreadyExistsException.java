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
<<<<<<< HEAD:src/main/java/az/lms/exception/AlreadyExistsException.java
    private final String message;
=======
    public StudentAlreadyExistsException(String message) {
        super(message);
    }
>>>>>>> 0a2bbcc (Author updated):src/main/java/az/lms/exception/StudentAlreadyExistsException.java
}
