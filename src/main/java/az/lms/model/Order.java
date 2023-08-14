package az.lms.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "student_id")
   private Long studentId;
   @Column(name = "book_id")
   private Long bookId;
   @Column(name = "order_time")
   private LocalDateTime orderTime;
   @Column(name = "order_type")
   @Enumerated(EnumType.STRING)
   private OrderType orderType;

}
