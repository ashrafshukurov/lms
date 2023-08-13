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

   private Long studentId;
   private Long bookId;
   private LocalDateTime orderTime;
   @Enumerated(EnumType.STRING)
   private OrderType orderType;

}
