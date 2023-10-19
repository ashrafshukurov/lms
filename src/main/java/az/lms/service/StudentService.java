package az.lms.service;

import az.lms.dto.request.StudentRequest;
import az.lms.dto.response.OrderResponse;
import az.lms.dto.response.StudentResponse;

import java.util.List;

/**
 * @author Mehman Osmanov on 12.08.23
 * @project LMS
 */
public interface StudentService {
   List<StudentResponse> getAll();
   void create(StudentRequest request);
   void update(StudentRequest request);
   StudentResponse getById(String fin);
   void deleteById(String fin);
   List<OrderResponse> getStudentOrders(String fin);

}
