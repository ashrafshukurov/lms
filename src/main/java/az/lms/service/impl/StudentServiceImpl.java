package az.lms.service.impl;

import az.lms.dto.request.StudentRequest;
import az.lms.dto.response.OrderResponse;
import az.lms.dto.response.StudentResponse;
import az.lms.enums.RoleType;
import az.lms.exception.AlreadyExistsException;
import az.lms.exception.NotFoundException;
import az.lms.mapper.OrderMapper;
import az.lms.mapper.StudentMapper;
import az.lms.model.Order;
import az.lms.model.Student;
import az.lms.repository.OrderRepository;
import az.lms.repository.StudentRepository;
import az.lms.security.PasswordEncoder;
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
   private final PasswordEncoder passwordCoderConfig;

   @Override
   public List<StudentResponse> getAll() {
      log.info("Getting all students");
      List<Student> students = studentRepository.findAll();
      return students.stream().map(studentMapper::entityToResponse).toList();
   }

   @Override
   public void create(StudentRequest request) {
      log.info("Creating new student account");
      if (!studentRepository.existsByFIN(request.getFIN())) {
         Student student = studentMapper.requestToEntity(request);
         student.setFIN(request.getFIN().toLowerCase());
         student.setPassword(passwordCoderConfig.passwordEncode(request.getPassword()));
         student.setRoleType(RoleType.STUDENT);
         studentRepository.save(student);
      } else throw new AlreadyExistsException("Student already exist with such fin code");
   }

   @Override
   public void update(StudentRequest request) {
      log.info("Updating student's fields");
      Student student = studentRepository.findByFIN(request.getFIN().toLowerCase()).orElseThrow(() -> new NotFoundException("Student not found with fin code"));
      Student newStudent = studentMapper.requestToEntity(request);
      newStudent.setId(student.getId());
      studentRepository.save(newStudent);
   }

   @Override
   public StudentResponse getById(String fin) {
      log.info("Getting student by fin {}", fin);
      Student student = studentRepository.findByFIN(fin.toLowerCase()).orElseThrow(() -> new NotFoundException("Student not found with fin code " + fin));
      return studentMapper.entityToResponse(student);
   }

   @Override
   public void deleteById(String fin) {
      log.warn("Deleting student account");
      Student student = studentRepository.findByFIN(fin.toLowerCase()).orElseThrow(() -> new NotFoundException("Student not found with fin code " + fin));
      studentRepository.delete(student);
   }

   @Override
   public List<OrderResponse> getStudentOrders(String fin) {
      log.warn("Getting all student's orders");
      Student student = studentRepository.findByFIN(fin.toLowerCase()).orElseThrow(() -> new NotFoundException("Student not found with fin=" + fin));
      List<Order> orders = orderRepository.findOrderByStudentId(student.getId());
      return orders.stream().map(orderMapper::entityToDto).toList();
   }

}