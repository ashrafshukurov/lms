package az.lms.repository;

import az.lms.model.Book;
import az.lms.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * @author ashraf
 * @project LMS
 */

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{
    boolean existsByIsbn(String isbn);
    Optional<Book> findByIsbn(String isbn);
    @Query(value = "SELECT b FROM Book b WHERE b.name LIKE %:bookName%")
    Optional<List<Book>> getBookByName(@Param("bookName") String bookName);



}
