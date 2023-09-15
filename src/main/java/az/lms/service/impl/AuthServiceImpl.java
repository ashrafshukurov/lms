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
import az.lms.exception.AlreadyExistsException;
import az.lms.exception.NotFoundException;
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

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordCoderConfig passwordEncoder;
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponse registration(StudentRequest user) {
        log.info("Start to find if student account is already exist");
        studentRepository.findByEmail(user.getEmail()).ifPresent(student -> {
            throw new AlreadyExistsException("Registration is already exist with email '" + user.getEmail() + "'");
        });
        Student student = studentMapper.requestToEntity(user);
        student.setPassword(passwordEncoder.passwordEncode(user.getPassword()));
        student.setRoleType(RoleType.STUDENT);
        return studentMapper.entityToResponse(studentRepository.save(student));
    }

    @Override
    public TokenResponse login(LoginRequest request) {
        log.info("Start to find if user is exist");
        Student student = studentRepository.findByEmail(request.getEmail()).orElseThrow(() ->
                new NotFoundException("User not found with email '" + request.getEmail() + "'"));
        if (student == null)
            throw new NotFoundException("User Not Found");
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
