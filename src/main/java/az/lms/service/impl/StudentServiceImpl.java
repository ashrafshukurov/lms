package az.lms.service.impl;

import az.lms.dto.request.StudentRequest;
import az.lms.dto.response.OrderResponse;
import az.lms.dto.response.StudentResponse;
import az.lms.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mehman Osmanov on 12.08.23
 * @project LMS
 */
@Service
public class StudentServiceImpl implements StudentService {
   @Override
   public List<StudentResponse> getAll() {
      return null;
   }

   @Override
   public void create(StudentRequest request) {

   }

   @Override
   public void update(StudentRequest request) {

   }

   @Override
   public StudentResponse getById(String fin) {
      return null;
   }

   @Override
   public void deleteById(String fin) {

   }

   @Override
   public List<OrderResponse> getStudentOrders(String fin) {
      return null;
   }
}
