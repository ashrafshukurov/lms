package az.lms.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private HttpStatus status;
    private LocalDateTime date;
    private String errorMessage;
    private int errorCode;

}
