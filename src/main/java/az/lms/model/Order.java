package az.lms.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */
@Entity
@Table(name = "orders")
@Setter
@Getter
public class Order {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private Long studentId;
   private Long bookId;
   private LocalDateTime orderTime;
   private OrderType orderType;

}
