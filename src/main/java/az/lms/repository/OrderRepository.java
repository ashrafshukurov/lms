package az.lms.repository;

import az.lms.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
   List<Order> findOrderByStudentId(Long id);

   @Query(value = " select o.order_type from orders o where student_id=?1 and book_id=?2 order by order_time desc limit 1"
           , nativeQuery = true)
   String getLastOrder(Long studentId, Long bookId);
}
