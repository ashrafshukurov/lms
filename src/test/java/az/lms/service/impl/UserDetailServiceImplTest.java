/*
package az.lms.service.impl;

import az.lms.enums.RoleType;
import az.lms.model.Student;
import az.lms.repository.LibrarianRepository;
import az.lms.repository.StudentRepository;
import az.lms.security.PasswordCoderConfig;
import az.lms.security.UserPricnipal.StudentPrincipal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDetailServiceImplTest {
    @InjectMocks
    UserDetailServiceImpl service;
    @Mock
    StudentRepository studentRepository;
    @Mock
    LibrarianRepository librarianRepository;
    @InjectMocks
    private UserDetailServiceImpl userDetailService;

    Student student = Student.builder()
            .id(1L)
            .name("Student")
            .surName("Student surname")
            .address("Student address")
            .birthDate(LocalDate.of(2012, 5, 3))
            .FIN("12345")
            .roleType(RoleType.STUDENT)
            .build();


    @Test
    void givenLoadUserByUsernameWhenUserFoundReturnUserDetails() {
       */
/* Set<GrantedAuthority> authorities = new HashSet<>();
        String studentEmail = "student@gmail.com";
        //arrange
        when(studentRepository.findByEmail(studentEmail)).thenReturn(Optional.of(student));
        when(true).thenReturn(true);
        studentPrincipal.setEmail(student.getEmail());
        studentPrincipal.setPassword(student.getPassword());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + student.getRoleType()));
        studentPrincipal.setAuthorities(authorities);
        //act
         service.loadUserByUsername(studentEmail);
        //assert
        verify(studentRepository, times(1)).findByEmail(studentEmail);
 *//*


        // Arrange
        String email = "student@example.com";
        Student student = new Student();
        student.setEmail(email);
        student.setPassword("password");

        when(studentRepository.findByEmail(email)).thenReturn(Optional.of(student));

        // Act
        UserDetails userDetails = userDetailService.loadUserByUsername(email);

        // Assert
        assertEquals(student.getEmail(), userDetails.getUsername());
        // Diğer özellikleri de kontrol edebilirsiniz.
    }


    @Test
    void givenLoadUserByUsernameWhenUserNotFoundThrowNotFoundException() {

    }
}*/
