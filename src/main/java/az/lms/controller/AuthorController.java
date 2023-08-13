/*
 *Created by Jaweed.Hajiyev
 *Date:12.08.23
 *TIME:01:21
 *Project name:LMS
 */

package az.lms.controller;

import az.lms.dto.request.AuthorRequest;
import az.lms.dto.response.AuthorResponse;
import az.lms.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService service;

    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestBody AuthorRequest request) {
        service.createAuthor(request);
        return ResponseEntity.ok("Successfully added");
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateAuthor(@RequestBody AuthorRequest request) {
        service.updateAuthors(request);
        return ResponseEntity.ok("Successfully updated");
    }
    @GetMapping("/get-all")
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        return ResponseEntity.ok(service.getAllAuthors());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAuthorById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        service.deleteAuthor(id);
        return ResponseEntity.ok("Student with ID " + id + " has been successfully deleted.");
    }
}
