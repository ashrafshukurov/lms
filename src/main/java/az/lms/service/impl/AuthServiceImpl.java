/*
 *Created by Jaweed.Hajiyev
 *Date:21.08.23
 *TIME:12:40
 *Project name:LMS
 */

package az.lms.service.impl;

import az.lms.dto.request.LoginRequest;
import az.lms.dto.request.StudentRequest;
import az.lms.dto.response.StudentResponse;
import az.lms.dto.response.TokenResponse;
import az.lms.enums.RoleType;
import az.lms.enums.TokenType;
import az.lms.mapper.StudentMapper;
import az.lms.model.Student;
import az.lms.repository.StudentRepository;
import az.lms.security.JwtTokenProvider;
import az.lms.security.PasswordCoderConfig;
import az.lms.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordCoderConfig passwordEncoder;
    private final StudentRepository repository;
    private final StudentMapper mapper;

    @Override
    public StudentResponse registration(StudentRequest user) {
        Student student = new Student();
        student.setEmail(user.getEmail());
        student.setPassword(passwordEncoder.passwordEncode(user.getPassword()));
        student.setFIN(user.getFIN());
        student.setStudentGroup(user.getStudentGroup());
        student.setBirthDate(user.getBirthDate());
        student.setName(user.getName());
        student.setSurName(user.getSurName());
        student.setAddress(user.getAddress());
        student.setRoleType(RoleType.STUDENT);
        return mapper.entityToResponse(repository.save(student));
    }
    @Override
    public TokenResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(jwtTokenProvider.generateToken((UserDetails) authentication.getPrincipal(), TokenType.ACCESS_TOKEN));
        tokenResponse.setRefreshToken(jwtTokenProvider.generateToken((UserDetails) authentication.getPrincipal(), TokenType.REFRESH_TOKEN));
        return tokenResponse;
    }
}
