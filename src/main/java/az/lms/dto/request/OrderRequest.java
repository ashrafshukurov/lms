package az.lms.dto.request;

import az.lms.model.OrderType;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */
@Data
public class OrderRequest {
   @NotBlank(message = "Student ID cannot be empty")
   private Long studentId;
   @NotBlank(message = "Book ID cannot be empty")
   private Long bookId;
   @NotBlank(message = "Order type cannot be empty")
   private OrderType orderType;
}
