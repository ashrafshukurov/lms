package az.lms.dto.response;

import az.lms.enums.OrderType;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */
@Data
public class OrderResponse {
   private Long studentId;
   private Long bookId;
   private LocalDateTime orderTime;
   private OrderType orderType;

}
