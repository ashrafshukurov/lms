package az.lms.controller;

import az.lms.LmsApplication;
import az.lms.dto.request.BookRequest;
import az.lms.dto.response.BookResponse;
import az.lms.model.Author;
import az.lms.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.bouncycastle.asn1.iana.IANAObjectIdentifiers.security;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * @author ashraf
 * @project LMS
 */
@SpringBootTest(classes = LmsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")

class BookControllerTest {
    @LocalServerPort
    private int port;
    private String url;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @BeforeEach
    void setUp(){
        this.url="http://localhost:"+port+"/lms";
    }
    @Test
    @Sql(scripts = "classpath:sql/book.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenGetBookByIdWhenFoundThenReturnResult(){
        Long id=1L;
        BookResponse bookResponse=new BookResponse();
        bookResponse.setId(id);
//        bookResponse.setName("book");
//        bookResponse.setCount(4);
//        bookResponse.setImage("image");
//        bookResponse.setIsbn("1234d");
//        bookResponse.setPublishedTime(LocalDate.of(2000,1,2));
//        Category category=new Category();
//        category.setName("category1");
//        category.setId(1L);
//        bookResponse.setCategory(category.getName());
//        List<String> authorSet=new ArrayList<>();
//        Author author=new Author();
//        author.setSurname("Authorov");
//        author.setName("Author");
//        authorSet.add(author.getName()+" "+author.getSurname());
//        bookResponse.setAuthorsName(authorSet);
        //act
        ResponseEntity<BookResponse> response=testRestTemplate.getForEntity(url+"/v1/book/"+id, BookResponse.class);
        //assert
        assertEquals(HttpStatus.OK,response.getStatusCode());

    }
    @Test
    @Sql(scripts = "classpath:sql/book.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenUpdateBookWhenFoundThenUpdate(){
        BookRequest bookRequest=new BookRequest();
        bookRequest.setIsbn("1234F");
    }






}