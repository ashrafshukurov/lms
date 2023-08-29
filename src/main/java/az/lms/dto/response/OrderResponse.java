package az.lms.dto.response;

import az.lms.enums.OrderType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */
@Setter
@Getter
public class OrderResponse {
   private Long studentId;
   private Long bookId;
   private LocalDateTime orderTime;
   private OrderType orderType;

}
