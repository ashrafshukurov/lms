package az.lms.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorMessage {
    private HttpStatus status;
    private LocalDateTime date;
    private String errorMessage;
    private int errorCode;

}
