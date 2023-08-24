/*
 *Created by Jaweed.Hajiyev
 *Date:12.08.23
 *TIME:01:21
 *Project name:LMS
 */

package az.lms.controller;

import az.lms.dto.request.AuthorRequest;
import az.lms.dto.response.AuthorResponse;
import az.lms.dto.response.BookResponse;
import az.lms.model.Book;
import az.lms.service.AuthorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Validated
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService service;

    @ApiOperation(value = "Create new author", notes = "Create new author")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created")
    })
    @PostMapping("/")
    public ResponseEntity<String> addAuthor(@Valid @RequestBody AuthorRequest request) {
        service.createAuthor(request);
        return ResponseEntity.ok("Successfully created");
    }

    @ApiOperation(value = "Update author by id", notes = "Update author by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "Author id not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateAuthor(@Valid @PathVariable Long id, @RequestBody AuthorRequest request) {
        service.updateAuthors(id, request);
        return ResponseEntity.ok("Successfully updated");
    }

    @ApiOperation(value = "Get all author", notes = "Get all author")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AuthorResponse.class),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping("/all")
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        return ResponseEntity.ok(service.getAllAuthors());
    }

    @ApiOperation(value = "Get  author by id", notes = "Get author by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AuthorResponse.class),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAuthorById(id));
    }

    @ApiOperation(value = "Delete author by id", notes = "Delete author by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "Author id not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        service.deleteAuthor(id);
        return ResponseEntity.ok("Author with ID " + id + " has been successfully deleted.");
    }

    @ApiOperation(value = "Get books by author id", notes = "Get books by  author id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = BookResponse.class),
            @ApiResponse(code = 404, message = "Author id not found")
    })
    @GetMapping("/{id}/book")
    public ResponseEntity<List<Book>> getBooksByAuthorId(@PathVariable(name = "id") Long authorId) {
        return ResponseEntity.ok(service.getBooksByAuthorId(authorId));
    }
}
