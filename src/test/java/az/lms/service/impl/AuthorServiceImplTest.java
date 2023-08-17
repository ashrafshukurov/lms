/*
package az.lms.service.impl;

import az.lms.dto.request.AuthorRequest;
import az.lms.model.Author;
import az.lms.model.Book;
import az.lms.model.Category;
import az.lms.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorRepository repository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void givenCreateAuthorWhenCreatedThenReturnResult() {
        //arrange
        Author author = Author.builder()
                .id(1L)
                .name("AuthorName")
                .surname("AuthorSurname")
                .biography("Some biography info")
                .birthDay(LocalDate.of(2023, 5, 5))
                .books(new HashSet<>())
                .build();

        Book book = Book.builder()
                .id(1L)
                .isbn("1234567890")
                .image("book_image.jpg")
                .count(5)
                .name("Book Name")
                .publishedTime(LocalDate.now())
                .authors(new HashSet<>())
                .categories(new Category())
                .build();
        //act
        Mockito.when(repository.save(author)).thenReturn(author);
        //assert
    }

    @Test
    void getAllAuthors() {
    }

    @Test
    void deleteAuthor() {
    }

    @Test
    void getAuthorById() {
    }

    @Test
    void updateAuthors() {
    }

    @Test
    void getBooksByAuthorId() {
    }
}*/
