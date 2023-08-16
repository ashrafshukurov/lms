/*
 *Created by Jaweed.Hajiyev
 *Date:13.08.23
 *TIME:23:10
 *Project name:LMS
 */

package az.lms.repository;

import az.lms.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}
