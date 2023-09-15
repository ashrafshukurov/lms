package az.lms.repository;

import az.lms.LmsApplication;
import az.lms.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * @author ashraf
 * @project LMS
 */
@SpringBootTest(classes = LmsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
@EnableConfigurationProperties
@EnableJpaRepositories
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    @Sql(scripts = "classpath:sql/book.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenFindByIsbnWhenFoundThenReturnEntity() {
        //arrange
        String isbn = "1234f";
        //act
        Optional<Book> result = bookRepository.findByIsbn(isbn);
        //assert
        assertFalse(result.isEmpty());
        Book book = result.get();
        assertEquals(isbn, book.getIsbn());

    }


}