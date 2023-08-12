package az.lms.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author ashraf
 * @project LMS
 */
@Setter
@Getter
public class ErrorResponse {
    private HttpStatus httpStatus;
    private String errorMessage;
    private LocalDateTime localDateTime;
    private int errorCode;
}
