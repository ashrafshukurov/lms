package az.lms.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Mehman Osmanov on 12.08.23
 * @project LMS
 */
@Setter
@Getter
@AllArgsConstructor
public class InsufficientCount extends RuntimeException {
   private final String message;
}
