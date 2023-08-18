package az.lms.controller;

import az.lms.dto.request.StudentRequest;
import az.lms.dto.response.BookResponse;
import az.lms.dto.response.OrderResponse;
import az.lms.dto.response.StudentResponse;
import az.lms.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Mehman Osmanov on 12.08.23
 * @project LMS
 */
@RestController
@RequestMapping("/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @ApiOperation(value = "adding student", notes = "add to student")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 400, message = "Invalid insert")
    })
    @PostMapping("/add")
    public void addStudent(@Valid @RequestBody StudentRequest studentRequest) {
        studentService.create(studentRequest);
    }

    @ApiOperation(value = "Get-Student-By-fin", notes = "When you enter fin it will get Student", response = StudentResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 404, message = "Invalid getting Student by fin")
    })
    @GetMapping("/getStudent/{fin}")
    public ResponseEntity<StudentResponse> getStudentById(@ApiParam(name = "FIN",value = "Student FIN",example = "5jh2ak8") @PathVariable String fin) {
        return ResponseEntity.ok(studentService.getById(fin));
    }

    @ApiOperation(value = "Update Student", notes = "Update Student based on fin")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 404, message = "Invalid update")
    })
    @PutMapping("/update")
    public void updateStudent(@Valid @RequestBody StudentRequest studentRequest) {
        studentService.update(studentRequest);
    }

    @ApiOperation(value = "Getting-All-Students", notes = "It Will return Student list", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 404, message = "Invalid getting Students")
    })
    @GetMapping("/all")
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @ApiOperation(value = "Deleting Student", notes = "Deleting Student based on Fin")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 404, message = "Invalid deleting student by fin")
    })
    @DeleteMapping("/delete/{fin}")
    public void deleteStudentByFin(@ApiParam(name = "FIN",value = "Student FIN",example = "5jh2ak8") @PathVariable String fin) {
        studentService.deleteById(fin);
    }
    @ApiOperation(value = "Get Student's Orders", notes = "When you pass Student fin you will get student's Order")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 404, message = "Invalid getting student's order by fin")
    })
    @GetMapping("/orders/{fin}")
    public ResponseEntity<List<OrderResponse>> getStudentOrders(@ApiParam(name = "FIN",value = "Student FIN",example = "5jh2ak8") @PathVariable String fin) {
        return ResponseEntity.ok(studentService.getStudentOrders(fin));
    }
}
