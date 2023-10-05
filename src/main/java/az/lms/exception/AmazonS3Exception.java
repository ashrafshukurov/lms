package az.lms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class AmazonS3Exception {
    private final HttpStatus httpStatus=HttpStatus.BAD_REQUEST;
    private final String message;
}
