package az.lms.controller;

import az.lms.dto.request.BookRequest;
import az.lms.dto.response.BookResponse;
import az.lms.service.BookService;
import az.lms.util.FileUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;


import java.util.List;

/**
 * @author ashraf
 * @project LMS
 */

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService;

    @ApiOperation(value = "adding book", notes = "add to Book and book picture")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 400, message = "Invalid insert")
    })
    @PostMapping("/")
    public void addBook(@RequestBody MultipartFile file, @Valid @ApiParam(name = "Object", value = "BookRequest")  BookRequest bookRequest) throws IOException {
         bookService.createBook(bookRequest, file);
    }

    @ApiOperation(value = "Update Book", notes = "Update Book based on id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 404, message = "Invalid update")
    })
    @PutMapping("/")
    public void updateBook(@Valid @RequestBody BookRequest bookRequest) {
        bookService.updateBook(bookRequest);
    }

    @ApiOperation(value = "Get-Book-By-Id", notes = "When you enter id it will return book", response = BookResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 404, message = "Invalid getting book by Id")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookByID(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @ApiOperation(value = "Getting-All-Books", notes = "It Will return Book list", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 404, message = "Invalid getting books")
    })

    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity<List<BookResponse>> getBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @ApiOperation(value = "Deleting Book", notes = "Deleting Book based on ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 404, message = "Invalid deleting book by Id")
    })
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @ApiOperation(value = "GetBookByName ", notes = "you can search book by name")

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully work"),
            @ApiResponse(code = 404, message = "Not Found book")
    })
    @GetMapping("/name/{bookname}")
    public ResponseEntity<List<BookResponse>> getBookByName(@PathVariable String bookname) {
        return ResponseEntity.ok(bookService.getBookByName(bookname));
    }
}
