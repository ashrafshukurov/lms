package az.lms.service.impl;

import az.lms.dto.request.StudentRequest;
import az.lms.dto.response.OrderResponse;
import az.lms.dto.response.StudentResponse;
import az.lms.exception.AlreadyExistsException;
import az.lms.exception.NotFoundException;
import az.lms.mapper.OrderMapper;
import az.lms.mapper.StudentMapper;
import az.lms.model.Order;
import az.lms.model.Student;
import az.lms.repository.OrderRepository;
import az.lms.repository.StudentRepository;
import az.lms.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mehman Osmanov on 12.08.23
 * @project LMS
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
   private final StudentRepository studentRepository;
   private final OrderRepository orderRepository;
   private final StudentMapper studentMapper;
   private final OrderMapper orderMapper;

   @Override
   public List<StudentResponse> getAll() {
      log.info("Getting all students");
      List<Student> students = studentRepository.findAll();
      return students.stream().map(studentMapper::entityToResponse).toList();
   }

   @Override
   public void create(StudentRequest request) {
      log.info("Creating new student account");
      if (studentRepository.existsByFinCode(request.getFinCode())) {
         Student student = studentMapper.requestToEntity(request);
         studentRepository.save(student);
      } else throw new AlreadyExistsException("Student already exist with such fin code");
   }

   @Override
   public void update(StudentRequest request) {
      log.info("Updating student's fields");
      Student student = studentRepository.findByFinCode(request.getFinCode());
      if (student == null)
         throw new NotFoundException("Student is not found with such fin code");
      Student newStudent = studentMapper.requestToEntity(request);
      newStudent.setId(student.getId());
      studentRepository.save(newStudent);
   }

   @Override
   public StudentResponse getById(String fin) {
      log.info("Getting student by fin {}",fin);
      Student student = studentRepository.findByFinCode(fin);
      if (student == null)
         throw new NotFoundException("Student is not found with such fin code");
      return studentMapper.entityToResponse(student);
   }

   @Override
   public void deleteById(String fin) {
      log.warn("Deleting student account");
      studentRepository.deleteByFinCode(fin);
   }

   @Override
   public List<OrderResponse> getStudentOrders(String fin) {
      log.warn("Getting all student's orders");
      Student student = studentRepository.findByFinCode(fin);
      List<Order> orders = orderRepository.findOrderByStudentId(student.getId());
      return orders.stream().map(orderMapper::entityToDto).toList();
   }
}
