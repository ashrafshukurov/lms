package az.lms.controller;

import az.lms.dto.response.AuthorResponse;
import az.lms.dto.response.BookResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * @author ashraf
 * @project LMS
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
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


    }

}