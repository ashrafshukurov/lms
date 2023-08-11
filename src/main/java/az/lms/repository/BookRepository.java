package az.lms.repository;

import az.lms.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ashraf
 * @project LMS
 */

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{


}
