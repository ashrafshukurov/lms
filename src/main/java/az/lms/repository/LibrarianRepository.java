/*
 *Created by Jaweed.Hajiyev
 *Date:22.08.23
 *TIME:16:17
 *Project name:LMS
 */

package az.lms.repository;

import az.lms.model.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian,Long > {
    Librarian findByEmail(String email);
    boolean existsByEmail(String email);

}
