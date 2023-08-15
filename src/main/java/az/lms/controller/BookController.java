package az.lms.controller;

import az.lms.dto.request.BookRequest;
import az.lms.dto.response.BookResponse;
import az.lms.dto.response.CategoryResponse;
import az.lms.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.time.LocalDate;
import java.util.List;

/**
 * @author ashraf
 * @project LMS
 */

@RestController
@RequestMapping("/v1/book")
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService;
    @PostMapping("/add")
    public void addBook(@RequestBody MultipartFile file,BookRequest bookRequest) throws IOException {
        bookService.createBook(bookRequest, file);
    }

    @PutMapping("/update")
    public void updateBook(@RequestBody BookRequest bookRequest) {
        bookService.updateBook(bookRequest);
    }

    @GetMapping("/getBook/{id}")
    public ResponseEntity<BookResponse> getBookByID(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookResponse>> getBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam String fileName) {
      return ResponseEntity.ok(bookService.downloadBookImage(fileName));
    }
    @GetMapping("/showCategory/{id}")
    public ResponseEntity<CategoryResponse> getCategoryByBook(@PathVariable Long id){
       return ResponseEntity.ok(bookService.showCategoriesByBook(id));
    }
    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        bookService.uploadFile(file);

    }

}
