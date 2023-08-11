package az.lms.dto.request;

import az.lms.model.OrderType;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */
@Data
public class OrderRequest {
   private Long studentId;
   private Long bookId;
   private OrderType orderType;
}
