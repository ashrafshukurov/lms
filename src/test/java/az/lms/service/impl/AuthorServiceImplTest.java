package az.lms.service.impl;

import az.lms.dto.request.AuthorRequest;
import az.lms.exception.NotFoundException;
import az.lms.mapper.impl.AuthorMapperImpl;
import az.lms.model.Author;
import az.lms.model.Book;
import az.lms.model.Category;
import az.lms.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorRepository repository;
    @InjectMocks
    private AuthorServiceImpl service;
    @Mock
    AuthorMapperImpl mapper;
    Set<Book> books = new HashSet<>();
    @BeforeEach
    void setUp() {

        books.add(book);
    }

    AuthorRequest request =
            AuthorRequest.builder()
                    .name("Author1")
                    .surname("SurnameAuthor1")
                    .biography("Author biography")
                    .birthDay(LocalDate.of(2020, 5, 5))
                    .build();

    Author author =
            Author.builder()
                    .id(1L)
                    .name("Author2")
                    .surname("SurnameAuthor")
                    .biography("Author biography")
                    .birthDay(LocalDate.of(2020, 5, 5))
                    .build();
    Book book = Book.builder()
            .name("Book1")
            .count(1)
            .categories(new Category())
            .isbn("1234")
            .details("Book1 details")
            .description("Book1 description")
            .build();




    @Test
    void givenCreateAuthorWhenCreatedThenReturnResult() {
        // Arrange
        when(mapper.requestToModel(request)).thenReturn(author);

        // Act
        service.createAuthor(request);

        // Assert
        verify(repository, times(1)).save(author);
        assertEquals("Author2", author.getName());
    }

    @Test
    void givenGetAllAuthorsWhenFoundThenReturnAllAuthor() {
        //arrange
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        when(repository.findAll()).thenReturn(authors);
        //act
        service.getAllAuthors();
        //assert
        verify(repository, times(1)).findAll();
        assertEquals("Author2", authors.get(0).getName());
    }

    @Test
    void givenDeleteAuthorWhenFoundThenDeleteAuthor() {
        //arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(author));
        //act
        service.deleteAuthor(id);
        //assert
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).delete(author);
    }

    @Test
    void givenDeleteAuthorWhenAuthorNotFoundThenThrowException() {
        //arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // act & assert
        assertThrows(NotFoundException.class, () -> service.deleteAuthor(id));
        verify(repository, times(1)).findById(id);
        verify(repository, never()).delete(author);
    }


    @Test
    void givenGetAuthorByIdWhenFoundThenReturnAuthor() {
        //arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(author));
        //act
        service.getAuthorById(id);
        //assert
        assertEquals("Author2", author.getName());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void givenGetAuthorByIdWhenNotFoundThenReturnException() {
        //arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        // act & assert
        assertThrows(NotFoundException.class, () -> service.getAuthorById(id));
        verify(repository, times(1)).findById(id);
    }

    @Test
    void givenUpdateAuthorsWhenAuthorFoundThenUpdate() {
        //arrance
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.of(author));
        when(mapper.requestToModel(request)).thenReturn(author);

        //act
        service.updateAuthors(id, request);

        //assert
        verify(repository, times(1)).findById(id);
        verify(mapper, times(1)).requestToModel(request);
        verify(repository, times(1)).save(author);
    }
    @Test
    void givenUpdateAuthorWhenNotFoundThenReturnException() {
        //arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // act & arrange
        assertThrows(NotFoundException.class, () -> service.updateAuthors(id,request));
        verify(repository, never()).save(author);
        verify(mapper, never()).requestToModel(request);
    }

    @Test
    void givenGetBooksByAuthorIdWhenFoundThenReturnBooksList() {
        //arrange

        //act

        //assert
    }

    @Test
    void givenGetBooksByAuthorIdWhenNotFoundThenThrowException() {
        //arrange

        //act

        //assert
    }
}
