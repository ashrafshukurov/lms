package az.lms.model;

import az.lms.enums.OrderType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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
//   @Column(unique = true)
   private Long id;

   @Column(name = "student_id")
   private Long studentId;
   @Column(name = "book_id")
   private Long bookId;
   @Column(name = "order_time")
   @CreationTimestamp
   private LocalDateTime orderTime;
   @Column(name = "order_type")
   @Enumerated(EnumType.STRING)
   private OrderType orderType;

}
