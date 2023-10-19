package az.lms.exception;

import az.lms.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import software.amazon.awssdk.services.s3.model.S3Exception;

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
            DataIntegrityViolationException.class,
            S3Exception.class
    })
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setDate(LocalDateTime.now());
        errorResponse.setStatus(getHttpStatus(ex));
        errorResponse.setErrorCode(errorResponse.getStatus().value());
        errorResponse.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResponse error = new ErrorResponse();
        String errorMessage = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse("Validation Error");
        error.setErrorMessage(errorMessage);
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setDate(LocalDateTime.now());
        error.setStatus(HttpStatus.BAD_REQUEST);
        log.error("Validation failed for argument: " + errorMessage);
        return error;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorResponse maxUploadSize(MaxUploadSizeExceededException e) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorMessage(e.getMessage());
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setDate(LocalDateTime.now());
        error.setStatus(HttpStatus.BAD_REQUEST);
        log.error("Validation failed for argument: " + e.getMessage());
        return error;
    }

    private HttpStatus getHttpStatus(Exception ex) {

        if ((ex instanceof AgeLimitException) || (ex instanceof MaxUploadSizeExceededException) || ex instanceof S3Exception) {
            return HttpStatus.BAD_REQUEST;
        } else if (ex instanceof NotFoundException) {
            return HttpStatus.NOT_FOUND;
        } else if ((ex instanceof AlreadyExistsException) ||
                (ex instanceof InsufficientCount) ||
                (ex instanceof DataIntegrityViolationException)) {
            return HttpStatus.CONFLICT;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }


}
