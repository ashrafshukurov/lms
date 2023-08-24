//package az.lms.controller;
//
//import az.lms.LmsApplication;
//import az.lms.dto.request.OrderRequest;
//import az.lms.dto.response.OrderResponse;
//import az.lms.model.OrderType;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.swagger.annotations.ApiKeyAuthDefinition;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.*;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.jdbc.Sql;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * @author Mehman Osmanov on 21.08.23
// * @project LMS
// */
//@SpringBootTest(classes = LmsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles(profiles = "test")
//class OrderControllerTest {
//
//   @LocalServerPort
//   private int serverPort;
//
//   private String url;
//
//   @BeforeEach
//   void setUp() {
//      url = "http://localhost:" + serverPort + "/lms/order";
//   }
//
//   @Autowired
//   private ObjectMapper objectMapper;
//   @Autowired
//   private TestRestTemplate restTemplate;
//
//   @Test
//   @Sql(scripts = "classpath:sql/category.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//   @Sql(scripts = "classpath:sql/book2.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//   @Sql(scripts = "classpath:sql/student.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//   @Sql(scripts = "classpath:sql/orders.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//   public void givenGetOrdersWhenFoundReturnList() {
//      //arrange & act
//      ResponseEntity<List<OrderResponse>> response = restTemplate.exchange(
//              url + "/", HttpMethod.GET, null,
//              new ParameterizedTypeReference<List<OrderResponse>>() {
//              });
//      //assert
//      assertEquals(HttpStatus.OK, response.getStatusCode());
//      List<OrderResponse> orders = response.getBody();
//      assertNotNull(orders);
//      assertFalse(orders.isEmpty());
//      assertEquals(orders.size(), 3);
//      assertEquals(orders.get(2).getOrderType(), OrderType.BORROWED);
//
//   }
//
//
//   @Test
//   @Sql(scripts = "classpath:sql/category.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//   @Sql(scripts = "classpath:sql/book2.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//   @Sql(scripts = "classpath:sql/student.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//   public void givenBorrowOrderWhenOrderedThenReturnOrderTypeOrdered() {
//      //arrange
//      OrderRequest orderRequest = new OrderRequest();
//      orderRequest.setStudentId(1L);
//      orderRequest.setBookId(1L);
//      orderRequest.setOrderType(OrderType.BORROWED);
//
//      //act
//      String response = restTemplate.postForObject(url + "/make", orderRequest, String.class);
////      restTemplate.postForEntity()
////      restTemplate.postForObject()
//      //assert
//      assertNotNull(response);
//      assertEquals("Successfully made return order", response);
//
//   }
//
//   @Test
//   @Sql(scripts = "classpath:sql/category.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//   @Sql(scripts = "classpath:sql/book2.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//   @Sql(scripts = "classpath:sql/student.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//   public void givenReturnOrderWhenOrderedThenReturnOrderTypeReturned() {
//      //arrange
//      OrderRequest orderRequest = new OrderRequest();
//      orderRequest.setStudentId(1L);
//      orderRequest.setBookId(1L);
//      orderRequest.setOrderType(OrderType.RETURNED);
//
//      //act
//      String response = restTemplate.postForObject(url + "/return", orderRequest, String.class);
//      //assert
//      assertNotNull(response);
//      assertEquals("Successfully made return return", response);
//
//   }
//
////
////   @Test
////   public void testBorrowOrderEndpoint() throws Exception {
////      // Create an example OrderRequest
////      OrderRequest request = new OrderRequest();
////      request.setOrderType(OrderType.BORROWED);
////      request.setBookId(1L);
////      request.setStudentId(1L);
////
////      HttpHeaders headers = new HttpHeaders();
////      headers.setContentType(MediaType.APPLICATION_JSON);
////
////      HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(request), headers);
////
////      ResponseEntity<String> response = restTemplate.exchange(
////              url + "/borrow",
////              HttpMethod.POST,
////              entity,
////              String.class
////      );
////
////      // Perform assertions on the response
////      assertEquals(200, response.getStatusCodeValue());
////      assertEquals("Successfully made borrow order", response.getBody());
//
//      // You can add more assertions based on your specific scenario
////   }
//}
//
