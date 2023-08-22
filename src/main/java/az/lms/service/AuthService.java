package az.lms.service;


import az.lms.dto.request.LoginRequest;
import az.lms.dto.request.StudentRequest;
import az.lms.dto.response.StudentResponse;
import az.lms.dto.response.TokenResponse;
import az.lms.model.Student;

public interface AuthService {
    TokenResponse login(LoginRequest request);

    StudentResponse registration(StudentRequest user);
}
