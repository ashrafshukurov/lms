package az.lms.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Mehman Osmanov on 12.08.23
 * @project LMS
 */
@Data
@AllArgsConstructor
public class InsufficientCount extends RuntimeException {
   private final String message;
}
