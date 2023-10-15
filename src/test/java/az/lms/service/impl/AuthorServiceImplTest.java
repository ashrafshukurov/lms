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

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    }
/*
    Author author = new Author();
        author.setId(1L);
        author.setName("Author1");
        author.setSurname("SurnameAuthor");
        author.setPassword("Aa123123");
        author.setBiography("Author biography");
        author.setEmail("author1@gmail.com");
        author.setBirthDay(LocalDate.of(2020, 5, 5));*/
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

    @Test
    void givenCreateAuthorWhenCreatedThenReturnResult() {
//         Arrange

        when(repository.existsByEmail(request.getEmail())).thenReturn(false);
        Author author = new Author();
        author.setRoleType(RoleType.AUTHOR);
        author.setBiography(request.getBiography());
        author.setName(request.getName());
        author.setId(1L);
        when(mapper.requestToModel(request)).thenReturn(author);
        when(passwordCoderConfig.passwordEncode(request.getPassword())).thenReturn("$2a$10$HviE7/sG8N41Wzhx9FluOO1dqPNV5CkU3DCUKvJgm9r4wLiRmfwi.");


        // Act
        service.createAuthor(request);

        // Assert
        verify(repository, times(1)).existsByEmail(request.getEmail());
        verify(repository, times(1)).save(author);
        assertEquals(RoleType.AUTHOR, author.getRoleType());
        assertEquals("$2a$10$HviE7/sG8N41Wzhx9FluOO1dqPNV5CkU3DCUKvJgm9r4wLiRmfwi.", author.getPassword());
    }

    @Test
    void givenCreateAuthorWhenThrowExcetpirThenReturnResult() {
        // Arrange

        /*when(repository.existsByEmail(request.getEmail())).thenReturn(false);

        Author author = new Author();
        when(mapper.requestToModel(request)).thenReturn(author);
        when(passwordCoderConfig.passwordEncode(request.getPassword())).thenReturn("$2a$10$HviE7/sG8N41Wzhx9FluOO1dqPNV5CkU3DCUKvJgm9r4wLiRmfwi.");
        author.setRoleType(RoleType.AUTHOR);

        // Act
        service.createAuthor(request);

        // Assert
        verify(repository, times(1)).existsByEmail(request.getEmail());
        verify(repository, times(1)).save(author);
        assertEquals(RoleType.AUTHOR, author.getRoleType());
        assertEquals("encodedPassword", author.getPassword());*/
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
