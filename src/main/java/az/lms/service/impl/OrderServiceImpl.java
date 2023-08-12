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
import az.lms.model.Student;
import az.lms.repository.BookRepository;
import az.lms.repository.OrderRepository;
import az.lms.repository.StudentRepository;
import az.lms.service.OrderService;
import az.lms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

   private final OrderRepository orderRepository;
   private final StudentRepository studentRepository;
   private final StudentService studentService;
   private final BookRepository bookRepository;
   private final OrderMapper orderMapper;


   @Override
   public List<OrderResponse> getOrdersList() {
      List<Order> orders = orderRepository.findAll();
      return orders.stream().map(orderMapper::entityToDto).toList();
   }

   @Transactional
   @Override
   public OrderType createOrder(OrderRequest request) {
      Student student = studentRepository.findById(request.getStudentId()).orElseThrow(
              () -> new NotFoundException("Student with ID " + request.getStudentId() + " not found"));
      Book book = bookRepository.findById(request.getBookId()).orElseThrow(
              () -> new NotFoundException("Book with ID " + request.getBookId() + " not found"));
      if (book.getCount() < 0)
         throw new InsufficientCount("This book is out of stock");
      boolean preOrdered= studentService.getStudentOrders(student.getFinCode()).stream().anyMatch(
              orderResponse -> orderResponse.getBookId().equals(request.getBookId())&&orderResponse.getOrderType().equals(OrderType.ORDERED));
      List<OrderResponse> orders = studentService.getStudentOrders(student.getFinCode());
      for (OrderResponse order : orders) {
//         book
      }

      if (preOrdered)
         throw new AlreadyExistsException("Order");
      Order order = Order.builder()
              .studentId(request.getStudentId())
              .bookId(request.getBookId())
              .orderType(OrderType.ORDERED)
              .orderTime(LocalDateTime.now()).build();
      book.setCount(book.getCount() - 1);
      bookRepository.save(book);
      orderRepository.save(order);


      return null;
   }

   @Override
   public OrderType returnOrder(OrderRequest request) {
      return null;
   }
}
