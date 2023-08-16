package az.lms.repository;

import az.lms.dto.request.AuthorRequest;
import az.lms.model.Author;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    boolean existsByNameAndSurnameAndBiographyAndBirthDay(String name, String surname, String biography, LocalDate birthday);

    Long getIdByNameAndSurnameAndBiographyAndBirthDay(String name, String surname, String biography, LocalDate birthday);
}
