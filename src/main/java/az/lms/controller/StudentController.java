package az.lms.controller;

import az.lms.dto.request.StudentRequest;
import az.lms.dto.response.OrderResponse;
import az.lms.dto.response.StudentResponse;
import az.lms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Mehman Osmanov on 12.08.23
 * @project LMS
 */
@RestController
@RequestMapping("/v1/student")
@RequiredArgsConstructor
public class StudentController  {
    private final StudentService studentService;

    @PostMapping("/add")
    public void addStudent(@RequestBody StudentRequest studentRequest){
        studentService.create(studentRequest);
    }
    @GetMapping("/{fin}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable String fin){
        return ResponseEntity.ok(studentService.getById(fin));
    }
    @PutMapping("/")
    public void updateStudent(@RequestBody StudentRequest studentRequest){
        studentService.update(studentRequest);
    }
    @GetMapping("/")
    public ResponseEntity<List<StudentResponse>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAll());
    }
    @DeleteMapping("{fin}")
    public void deleteStudentByFin(@PathVariable String fin){
        studentService.deleteById(fin);
    }
    @GetMapping("/orders/{fin}")
    public ResponseEntity<List<OrderResponse>> getStudentOrders(@PathVariable String fin){
        return ResponseEntity.ok(studentService.getStudentOrders(fin));
    }
}
