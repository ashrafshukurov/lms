package az.lms.repository;

import az.lms.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
