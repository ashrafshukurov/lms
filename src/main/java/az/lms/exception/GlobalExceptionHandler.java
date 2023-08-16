package az.lms.exception;

import az.lms.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.time.LocalDateTime;

/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {
            AgeLimitException.class,
            NotFoundException.class,
            AlreadyExistsException.class,
            InsufficientCount.class,
            MaxUploadSizeExceededException.class
    })
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.info(ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setDate(LocalDateTime.now());
        errorResponse.setStatus(getHttpStatus(ex));
        errorResponse.setErrorCode(errorResponse.getStatus().value());
        errorResponse.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }
    private HttpStatus getHttpStatus(Exception ex) {
        if ((ex instanceof AgeLimitException) || (ex instanceof MaxUploadSizeExceededException)) {
            return HttpStatus.BAD_REQUEST;
        } else if (ex instanceof NotFoundException) {
            return HttpStatus.NOT_FOUND;
        } else if ((ex instanceof AlreadyExistsException) || (ex instanceof InsufficientCount)) {
            return HttpStatus.CONFLICT;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }


}
