package az.lms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author ashraf
 * @project LMS
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotFoundBookException extends RuntimeException {
    private HttpStatus httpStatus=HttpStatus.NOT_FOUND;
    private String msg;
}
