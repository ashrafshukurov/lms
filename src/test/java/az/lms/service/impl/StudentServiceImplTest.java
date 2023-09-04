package az.lms.service.impl;

import az.lms.dto.request.StudentRequest;
import az.lms.dto.response.StudentResponse;
import az.lms.enums.RoleType;
import az.lms.exception.AlreadyExistsException;
import az.lms.mapper.StudentMapper;
import az.lms.model.Book;
import az.lms.model.Student;
import az.lms.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;


import java.time.LocalDate;
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
    @InjectMocks
    private Book book;
    @BeforeEach
    void setStudent(){
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
    public void givenGetStudentsWhenFoundThenReturnList(){
        StudentResponse studentResponse=new StudentResponse();
        studentResponse.setAddress(student.getAddress());
        studentResponse.setStudentGroup(student.getStudentGroup());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setName(student.getName());
        studentResponse.setSurName(student.getSurName());
        studentResponse.setFIN(student.getFIN());

        when(studentRepository.findAll()).thenReturn(List.of(student));
        when(studentMapper.entityToResponse(student)).thenReturn(studentResponse);

        //act
        List<StudentResponse> studentResponses=studentService.getAll();
        //assert
        assertNotNull(studentResponses);
        assertEquals(studentResponses.get(0).getFIN(),studentResponse.getFIN());
        assertEquals(studentResponses.get(0).getStudentGroup(),studentResponse.getStudentGroup());
        assertEquals(studentResponses.get(0).getName(),studentResponse.getName());
    }
    @Test
    public void givenGetsStudentsWhenNotFoundThenReturnEmpty(){
        //arrange
        List<Student> students=new ArrayList<>();
        when(studentRepository.findAll()).thenReturn(students);
        //act
        List<StudentResponse> studentResponses=studentService.getAll();
        //assert
        assertNotNull(studentResponses);
        assertTrue(studentResponses.isEmpty());
    }
    @Test
    public void validCreatingStudent(){
        //arrange
        String fin="123L";
        Student student1=new Student();
        student1.setFIN(fin);
        StudentRequest request=new StudentRequest();
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
    public void givenCreateStudentWhenThrowAlreadyException(){
       //arrange
        String fin="123L";
        StudentRequest studentRequest=new StudentRequest();
        studentRequest.setFIN(fin);
        when(studentRepository.existsByFIN(fin)).thenReturn(false);
        //act & assert
        assertThrows(AlreadyExistsException.class,()->studentService.create(studentRequest));
        verify(studentRepository, times(1)).existsByFIN(studentRequest.getFIN());
        verify(studentMapper, never()).requestToEntity(any());
        verify(studentRepository, never()).save(any(Student.class));
    }


}