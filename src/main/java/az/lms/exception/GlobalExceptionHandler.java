package az.lms.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ashraf
 * @project LMS
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundBookException.class)
    public ResponseEntity<?> notFountBook(NotFoundBookException ex){

    }
}
