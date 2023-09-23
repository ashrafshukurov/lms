package az.lms.controller;

import az.lms.LmsApplication;
import az.lms.dto.request.BookRequest;
import az.lms.dto.response.BookResponse;
import az.lms.model.Author;
import az.lms.model.Category;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
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
    @Autowired
    private ObjectMapper objectMapper;
    @BeforeEach
    void setUp(){
        this.url="http://localhost:"+port+"/lms/v1/book";
    }

    @Test
    @Sql(scripts = "classpath:sql/category.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/book.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

    public void givenGetBookByIdWhenFoundThenReturnResult(){
        Long id=1L;
        BookResponse bookResponse=new BookResponse();
        bookResponse.setId(id);
        bookResponse.setName("book");
        bookResponse.setCount(4);
        bookResponse.setImage("image");
        bookResponse.setIsbn("1234d");
        bookResponse.setPublishedTime(LocalDate.of(2000,1,2));
        Category category=new Category();
        category.setName("category1");
        category.setId(1L);
        bookResponse.setCategory(category.getName());
        List<String> authorSet=new ArrayList<>();
        Author author=new Author();
        author.setSurname("Authorov");
        author.setName("Author");
        authorSet.add(author.getName()+" "+author.getSurname());
        bookResponse.setAuthorsName(authorSet);
        //act
        ResponseEntity<BookResponse> response=testRestTemplate.getForEntity(url+"/"+id, BookResponse.class);
        //assert
        assertEquals(HttpStatus.OK,response.getStatusCode());

    }
    @Test
    @Sql(scripts = "classpath:sql/category.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/book.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenUpdateBookWhenFoundThenUpdate() throws JsonProcessingException {
        //arrange
        BookRequest bookRequest=new BookRequest();
        bookRequest.setIsbn("1234f");
        bookRequest.setCategories_id(1L);
        bookRequest.setDescription("description");
        bookRequest.setCount(12);
        bookRequest.setImage("123wes");
        bookRequest.setName("Book13");
        bookRequest.setPublishedTime(LocalDate.of(2001,2,2));


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(objectMapper.writeValueAsString(bookRequest), headers);
        //act
        ResponseEntity<String> responseEntity=testRestTemplate.exchange(url+"/",HttpMethod.PUT,requestEntity,String.class);
        //assert
        assertEquals(200,responseEntity.getStatusCodeValue());

    }
    @Test
    @Sql(scripts = "classpath:sql/category.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/book.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenGetAllBookWhenFoundThenReturnList(){
        //act &assert
        ResponseEntity<List> responseEntity = testRestTemplate.getForEntity(url+"/", List.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Sql(scripts = "classpath:sql/category.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/book.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenDeleteBookByIdWhenFoundThenDelete(){
        //arrange
        Long id=1L;
        ResponseEntity<String> responseEntity=testRestTemplate.exchange(url+"/"+id,HttpMethod.DELETE,null,String.class);
        assertEquals(200,responseEntity.getStatusCodeValue());
    }
    @Test
    @Sql(scripts = "classpath:sql/category.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/book.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenGetBookByNameWhenFoundThenReturnResponse(){
        BookResponse bookResponse=new BookResponse();
        String bookName="sherlock";
        bookResponse.setName(bookName);
        ResponseEntity<BookResponse> response=testRestTemplate.getForEntity(url+"/name/"+bookName, BookResponse.class);
        assertEquals(200,response.getStatusCodeValue());
    }






}