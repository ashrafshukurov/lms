package az.lms.service.impl;

import az.lms.dto.request.OrderRequest;
import az.lms.dto.response.OrderResponse;
import az.lms.exception.AlreadyExistsException;
import az.lms.exception.InsufficientCount;
import az.lms.exception.NotFoundException;
import az.lms.mapper.OrderMapper;
import az.lms.model.Book;
import az.lms.model.Order;
import az.lms.model.OrderType;
import az.lms.repository.BookRepository;
import az.lms.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Mehman Osmanov on 21.08.23
 * @project LMS
 */
@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

   @InjectMocks
   private OrderServiceImpl orderService;
   @Mock
   private OrderMapper orderMapper;
   @Mock
   private OrderRepository orderRepository;
   @Mock
   private BookRepository bookRepository;
   private Order order;
   private Book book;

   @BeforeEach
   void setOrder() {
      order = new Order();
      order.setStudentId(1L);
      order.setBookId(1L);
      order.setOrderType(OrderType.BORROWED);
      order.setOrderTime(LocalDateTime.now());
      order.setId(1L);

      book = Book.builder()
              .id(1L)
              .name("Test Book")
              .image("Test image")
              .count(3)
              .isbn("isbn")
              .description("Test description")
              .build();

   }


   @Test
   public void givenGetOrdersWhenFoundThenReturnOrderList() {
      //arrange
      OrderResponse orderResponse = new OrderResponse();
      orderResponse.setStudentId(order.getStudentId());
      orderResponse.setBookId(order.getBookId());
      orderResponse.setOrderType(order.getOrderType());
      orderResponse.setOrderTime(order.getOrderTime());

      when(orderRepository.findAll()).thenReturn(List.of(order));
      when(orderMapper.entityToDto(order)).thenReturn(orderResponse);

      //act
      List<OrderResponse> responses = orderService.getOrders();

      //assert
      assertNotNull(responses);
      assertTrue(responses.contains(orderResponse));
      assertEquals(responses.get(0).getOrderType(), order.getOrderType());
      assertEquals(responses.get(0).getStudentId(), order.getStudentId());
      assertEquals(responses.get(0).getBookId(), order.getBookId());
      assertEquals(responses.get(0).getOrderTime(), order.getOrderTime());

   }

   @Test
   public void givenGetOrdersWhenNotFoundThenReturnEmptyList() {
      //arrange
      List<Order> responses = new ArrayList<>();
      when(orderRepository.findAll()).thenReturn(responses);
      //act
      List<OrderResponse> orderResponses = orderService.getOrders();
      //assert
      assertNotNull(orderResponses);
      assertTrue(orderResponses.isEmpty());

   }


   @Test
   public void givenBorrowOrderWhenBorrowedThenReturnSuccessMessage() {
      //arrange
      OrderRequest orderRequest = new OrderRequest();
      orderRequest.setOrderType(OrderType.BORROWED);
      orderRequest.setBookId(1L);
      orderRequest.setStudentId(1L);

      when(bookRepository.findById(orderRequest.getBookId())).thenReturn(Optional.of(book));
      when(orderRepository.getLastOrder(1L, 1L)).thenReturn(null);
      when(orderMapper.dtoToEntity(orderRequest)).thenReturn(order);
      //act

      String response = orderService.borrowOrder(orderRequest);
      //assert
      assertNotNull(response);
      assertEquals("Successfully made borrow order", response);
   }

   @Test
   public void givenBorrowOrderWhenNotBorrowedThenReturnBookNotFoundException() {
      //arrange
      OrderRequest orderRequest = new OrderRequest();
      orderRequest.setOrderType(OrderType.BORROWED);
      orderRequest.setBookId(100L);
      orderRequest.setStudentId(1L);

      when(bookRepository.findById(orderRequest.getBookId())).thenReturn(Optional.empty());

      //act & assert
      Assertions.assertThatThrownBy(() -> orderService.borrowOrder(orderRequest))
              .isInstanceOf(NotFoundException.class)
              .hasMessage("Book with ID " + orderRequest.getBookId() + " not found");
   }

   @Test
   public void givenBorrowOrderWhenNotBorrowedThenReturnInsufficientCountException() {
      //arrange
      Book book = Book.builder()
                      .id(1L)
                      .name("Test Book")
                      .image("Test image")
                      .count(0)
                      .isbn("isbn")
                      .description("Test description").build();
      OrderRequest orderRequest = new OrderRequest();
      orderRequest.setOrderType(OrderType.BORROWED);
      orderRequest.setBookId(1L);
      orderRequest.setStudentId(1L);

      when(bookRepository.findById(orderRequest.getBookId())).thenReturn(Optional.of(book));

      //act & assert
      Assertions.assertThatThrownBy(() -> orderService.borrowOrder(orderRequest))
              .isInstanceOf(InsufficientCount.class)
              .hasMessage("This book is out of stock");
   }

   @Test
   public void givenBorrowOrderWhenNotBorrowedThenReturnOrderAlreadyExistsException() {
      //arrange
      OrderRequest orderRequest = new OrderRequest();
      orderRequest.setOrderType(OrderType.BORROWED);
      orderRequest.setBookId(1L);
      orderRequest.setStudentId(1L);

      when(bookRepository.findById(orderRequest.getBookId())).thenReturn(Optional.of(book));
      when(orderRepository.getLastOrder(1L, 1L)).thenReturn("ORDERED");

      //act & assert
      Assertions.assertThatThrownBy(() -> orderService.borrowOrder(orderRequest))
              .isInstanceOf(AlreadyExistsException.class)
              .hasMessage("You have already taken the book!");
   }


   @Test
   public void givenReturnOrderWhenReturnThenReturnSuccessMessage() {
      //arrange
      OrderRequest returnRequest = new OrderRequest();
      returnRequest.setOrderType(OrderType.RETURNED);
      returnRequest.setBookId(1L);
      returnRequest.setStudentId(1L);

      when(bookRepository.findById(returnRequest.getBookId())).thenReturn(Optional.of(book));
      when(orderRepository.getLastOrder(1L, 1L)).thenReturn(order.getOrderType().name());
      when(orderMapper.dtoToEntity(returnRequest)).thenReturn(order);
      //act

      String response = orderService.returnOrder(returnRequest);
      //assert
      assertNotNull(response);
      assertEquals("Successfully made return order", response);
   }


   @Test
   public void givenReturnOrderWhenNotBorrowedThenReturnBookNotFoundException() {
      //arrange
      OrderRequest returnRequest = new OrderRequest();
      returnRequest.setOrderType(OrderType.RETURNED);
      returnRequest.setBookId(100L);
      returnRequest.setStudentId(1L);

      when(bookRepository.findById(returnRequest.getBookId())).thenReturn(Optional.empty());

      //act & assert
      Assertions.assertThatThrownBy(() -> orderService.borrowOrder(returnRequest))
              .isInstanceOf(NotFoundException.class)
              .hasMessage("Book with ID " + returnRequest.getBookId() + " not found");
   }

   @Test
   public void givenReturnedOrderWhenNotBorrowedThenReturnOrderNotFoundException() {
      //arrange
      OrderRequest returnRequest = new OrderRequest();
      returnRequest.setOrderType(OrderType.RETURNED);
      returnRequest.setBookId(1L);
      returnRequest.setStudentId(1L);

      when(bookRepository.findById(returnRequest.getBookId())).thenReturn(Optional.of(book));
      when(orderRepository.getLastOrder(1L, 1L)).thenReturn(null);

      //act & assert
      Assertions.assertThatThrownBy(() -> orderService.returnOrder(returnRequest))
              .isInstanceOf(NotFoundException.class)
              .hasMessage("You have not taken the book!");
   }

}
