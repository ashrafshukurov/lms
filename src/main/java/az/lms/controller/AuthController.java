/*
 *Created by Jaweed.Hajiyev
 *Date:21.08.23
 *TIME:12:47
 *Project name:LMS
 */

package az.lms.controller;

import az.lms.dto.request.LoginRequest;
import az.lms.dto.request.StudentRequest;
import az.lms.dto.response.StudentResponse;
import az.lms.dto.response.TokenResponse;
import az.lms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.accepted().body(authService.login(request));
    }

    @PostMapping("/registration")
    public ResponseEntity<StudentResponse> studentRegistration(@Valid @RequestBody StudentRequest request) {
        return ResponseEntity.ok(authService.registration(request));
    }
}
