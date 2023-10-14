package az.lms.service.impl;

import az.lms.dto.request.AuthorRequest;
import az.lms.dto.response.AuthorResponse;
import az.lms.enums.RoleType;
import az.lms.mapper.AuthorMapper;
import az.lms.model.Author;
import az.lms.repository.AuthorRepository;
import az.lms.security.PasswordCoderConfig;
import az.lms.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.rmi.AlreadyBoundException;
import java.time.LocalDate;

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
    private PasswordCoderConfig passwordCoderConfig;
    @Mock
    AuthorMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
     /*   Author author = new Author();
        author.setId(1L);
        author.setName("Author1");
        author.setSurname("SurnameAuthor");
        author.setPassword(passwordCoderConfig.passwordEncode("Aa123123"));
        author.setBiography("Author biography");
        author.setEmail("author1@gmail.com");
        author.setBirthDay(LocalDate.of(2020, 5, 5));
        author.setRoleType(RoleType.AUTHOR);*/
    }


    AuthorRequest request =
            AuthorRequest.builder()
                    .name("Author1")
                    .surname("SurnameAuthor")
                    .biography("Author biography")
                    .email("author1@gmail.com")
                    .birthDay(LocalDate.of(2020, 5, 5))
                    .build();
    AuthorResponse response =
            AuthorResponse.builder()
                    .id(1L)
                    .name("Author1")
                    .surname("SurnameAuthor")
                    .biography("Author biography")
                    .email("author1@gmail.com")
                    .birthDay(LocalDate.of(2020, 5, 5))
                    .build();

    Author author =
            Author.builder()
                    .id(1L)
                    .name("Author1")
                    .surname("SurnameAuthor")
                    .biography("Author biography")
                    .email("author1@gmail.com")
                    .birthDay(LocalDate.of(2020, 5, 5))
                    .build();

    @Test
    void givenCreateAuthorWhenCreatedThenReturnResult() {
        // Arrange
        when(repository.existsByEmail(request.getEmail())).thenReturn(false);
        when(mapper.requestToModel(request)).thenReturn(author);

        // Act
        service.createAuthor(request);

        // Assert
     /*  verify(repository, times(1)).existsByEmail(request.getEmail());
        verify(repository, times(1)).save(author);*/
        assertEquals(RoleType.AUTHOR, author.getRoleType());
    }

    @Test
    void givenCreateAuthorWhenAuthorIsPresentThenThrowException() {
        // Arrange
        String email = "author1@gmail.com";
        when(repository.existsByEmail(email)).thenReturn(true);

        // act & assert
        service.createAuthor(request);
        verify(repository, times(1)).existsByEmail(email);
        assertThrows(AlreadyBoundException.class, () -> service.createAuthor(request));
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
}
