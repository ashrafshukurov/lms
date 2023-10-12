package az.lms.service.impl;

import az.lms.dto.request.StudentRequest;
import az.lms.dto.response.OrderResponse;
import az.lms.dto.response.StudentResponse;
import az.lms.enums.RoleType;
import az.lms.exception.AlreadyExistsException;
import az.lms.exception.NotFoundException;
import az.lms.mapper.OrderMapper;
import az.lms.mapper.StudentMapper;
import az.lms.model.Book;
import az.lms.model.Order;
import az.lms.model.Student;
import az.lms.repository.OrderRepository;
import az.lms.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ashraf
 * @project LMS
 */
@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    @InjectMocks
    private StudentServiceImpl studentService;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;
    @InjectMocks
    private Student student;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;
    @InjectMocks
    private Book book;

    @BeforeEach
    void setStudent() {
        student.setName("Student");
        student.setId(122L);
        student.setStudentGroup("121A");
        student.setEmail("Student12@gmail.com");
        student.setAddress("Baku,Azerbaijan");
        student.setSurName("Testov");
        student.setPassword("student123");
        student.setRoleType(RoleType.ADMIN);
        student.setFIN("12345");
    }

    @Test
    public void givenGetStudentsWhenFoundThenReturnList() {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setAddress(student.getAddress());
        studentResponse.setStudentGroup(student.getStudentGroup());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setName(student.getName());
        studentResponse.setSurName(student.getSurName());
        studentResponse.setFIN(student.getFIN());

        when(studentRepository.findAll()).thenReturn(List.of(student));
        when(studentMapper.entityToResponse(student)).thenReturn(studentResponse);

        //act
        List<StudentResponse> studentResponses = studentService.getAll();
        //assert
        assertNotNull(studentResponses);
        assertEquals(studentResponses.get(0).getFIN(), studentResponse.getFIN());
        assertEquals(studentResponses.get(0).getStudentGroup(), studentResponse.getStudentGroup());
        assertEquals(studentResponses.get(0).getName(), studentResponse.getName());
    }

    @Test
    public void givenGetsStudentsWhenNotFoundThenReturnEmpty() {
        //arrange
        List<Student> students = new ArrayList<>();
        when(studentRepository.findAll()).thenReturn(students);
        //act
        List<StudentResponse> studentResponses = studentService.getAll();
        //assert
        assertNotNull(studentResponses);
        assertTrue(studentResponses.isEmpty());
    }

    @Test
    public void validCreatingStudent() {
        //arrange
        String fin = "123L";
        Student student1 = new Student();
        student1.setFIN(fin);
        StudentRequest request = new StudentRequest();
        request.setFIN(fin);
        when(studentRepository.existsByFIN(fin)).thenReturn(true);
        when(studentMapper.requestToEntity(request)).thenReturn(student1);
        //act
        studentService.create(request);
        verify(studentRepository, times(1)).existsByFIN(request.getFIN());
        verify(studentMapper, times(1)).requestToEntity(request);
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    public void givenCreateStudentWhenThrowAlreadyException() {
        //arrange
        String fin = "123L";
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setFIN(fin);
        when(studentRepository.existsByFIN(fin)).thenReturn(false);
        //act & assert
        assertThrows(AlreadyExistsException.class, () -> studentService.create(studentRequest));
        verify(studentRepository, times(1)).existsByFIN(studentRequest.getFIN());
        verify(studentMapper, never()).requestToEntity(any());
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    public void givenUpdateStudentThenUpdate() {
        //arrange
        Student student1 = new Student();
        student1.setFIN("1234F");
        student1.setId(1234L);
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setFIN("1234F");

        //act

        when(studentRepository.findByFIN(student1.getFIN())).thenReturn(Optional.of(student1));
        when(studentMapper.requestToEntity(studentRequest)).thenReturn(student1);
        studentService.update(studentRequest);
        //assert
        verify(studentRepository, times(1)).findByFIN(student1.getFIN());
        verify(studentMapper, times(1)).requestToEntity(studentRequest);
        verify(studentRepository, times(1)).save(student1);
    }

    @Test
    public void givenUpdateStudentWhenThrowEmpty() {
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setFIN("1234F");
        when(studentRepository.findByFIN(studentRequest.getFIN())).thenReturn(Optional.empty());
        //act && assert
        assertThrows(NotFoundException.class, () -> studentService.update(studentRequest));
        verify(studentRepository, times(1)).findByFIN(studentRequest.getFIN());
        verify(studentMapper, never()).requestToEntity(studentRequest);
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    public void givenGetByIdWhenFoundThenReturnResult() {
        //arrange
        String fin = "1232F";
        Student student1 = new Student();
        student1.setId(12L);
        student1.setFIN(fin);
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setFIN(fin);
        when(studentRepository.findByFIN(fin)).thenReturn(Optional.of(student1));
        when(studentMapper.entityToResponse(student1)).thenReturn(studentResponse);
        //act
        StudentResponse studentResponse1 = studentService.getById(fin);
        //assert
        assertEquals(fin, studentResponse1.getFIN());
        verify(studentRepository, times(1)).findByFIN(studentResponse.getFIN());
    }

    @Test
    public void givenGetByIdWhenNotFoundThenThrow404() {
        //arrange
        String fin = "123d";
        when(studentRepository.findByFIN(fin)).thenReturn(Optional.empty());
        //act && assert
        assertThrows(NotFoundException.class, () -> studentService.getById(fin));
        verify(studentRepository, times(1)).findByFIN(fin);
    }

    @Test
    public void givenDeleteStudentByIdWhenFoundThenDelete() {
        //arrange
        String fin = "123df";
        Student student1 = new Student();
        student1.setFIN(fin);
        when(studentRepository.findByFIN(fin)).thenReturn(Optional.of(student1));
        //act
        studentService.deleteById(fin);
        //assert
        verify(studentRepository, times(1)).findByFIN(fin);
    }

    @Test
    public void givenDeleteStudentByIdWhenNotFoundThenThrow404() {
        //arrange
        String fin = "123a";
        when(studentRepository.findByFIN(fin)).thenReturn(Optional.empty());
        //act & assert
        assertThrows(NotFoundException.class, () -> studentService.deleteById(fin));
        verify(studentRepository, times(1)).findByFIN(fin);
    }

    @Test
    public void givenGetStudentOrdersWhenFoundThenReturnResult() {
        //arrange
        String fin = "12345";
        when(studentRepository.findByFIN(fin)).thenReturn(Optional.of(student));

        Order order = new Order();
        order.setStudentId(student.getId());
        order.setOrderTime(LocalDateTime.now());
        order.setBookId(1L);
        order.setId(2L);
        when(orderRepository.findOrderByStudentId(student.getId())).thenReturn(List.of(order));
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderType(order.getOrderType());
        orderResponse.setStudentId(order.getStudentId());
        orderResponse.setBookId(order.getBookId());
        orderResponse.setOrderTime(order.getOrderTime());
        when(orderMapper.entityToDto(order)).thenReturn(orderResponse);
        List<OrderResponse> orderResponses = studentService.getStudentOrders(fin);
        assertNotNull(orderResponses);
        assertEquals(orderResponses.get(0).getStudentId(), orderResponse.getStudentId());
        assertEquals(orderResponses.get(0).getOrderTime(), orderResponse.getOrderTime());
        assertEquals(orderResponses.get(0).getOrderType(), orderResponse.getOrderType());
        assertEquals(orderResponses.get(0).getBookId(), orderResponse.getBookId());


    }
    @Test
    public void givenGetStudentOrderWhenNotFoundThenThrowException(){
        //arrange
        String fin="12345f";
        when(studentRepository.findByFIN(fin)).thenReturn(Optional.empty());
        //act & assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> studentService.getStudentOrders(fin));
        assertEquals("Student not found with fin=" + fin, exception.getMessage());

        verify(studentRepository).findByFIN(fin);
        verifyNoInteractions(orderRepository); // Ensure orderRepository was not called
        verifyNoInteractions(orderMapper);


    }

}