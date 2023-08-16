/*
 *Created by Jaweed.Hajiyev
 *Date:12.08.23
 *TIME:01:21
 *Project name:LMS
 */

package az.lms.controller;

import az.lms.dto.request.AuthorRequest;
import az.lms.dto.response.AuthorResponse;
import az.lms.model.Book;
import az.lms.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/v1/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService service;

    @PostMapping("/add")
    public ResponseEntity<String> addAuthor( @RequestBody AuthorRequest request) {
        service.createAuthor(request);
        return ResponseEntity.ok("Successfully added");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateAuthor(@PathVariable Long id, @RequestBody AuthorRequest request) {
        service.updateAuthors(id, request);
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
        return ResponseEntity.ok("Author with ID " + id + " has been successfully deleted.");
    }

    @GetMapping("/get-books-by-author-id/{id}")
    public ResponseEntity<List<Book>> getBooksByAuthorId(@PathVariable(name = "id") Long authorId) {
        return ResponseEntity.ok(service.getBooksByAuthorId(authorId));
    }
}
