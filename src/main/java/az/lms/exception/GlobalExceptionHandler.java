package az.lms.exception;

import az.lms.dto.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * @author ashraf
 * @project LMS
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundBookException.class)
    public ResponseEntity<?> notFountBook(NotFoundBookException ex){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setErrorCode(ex.getHttpStatus().value());
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setHttpStatus(ex.getHttpStatus());
        errorResponse.setLocalDateTime(LocalDateTime.now());
        return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
    }
}
