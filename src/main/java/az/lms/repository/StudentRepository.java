package az.lms.repository;

import az.lms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ashraf
 * @project LMS
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

   Student findByFinCode(String finCode);

   void deleteByFinCode(String finCode);
}
