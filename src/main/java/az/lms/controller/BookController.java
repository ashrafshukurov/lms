package az.lms.controller;

import az.lms.dto.request.BookRequest;
import az.lms.dto.response.BookResponse;
import az.lms.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public void addBook(@RequestBody BookRequest bookRequest){
        bookService.createBook(bookRequest);
    }

    @PutMapping("/update")
    public void updateBook(@RequestBody BookRequest bookRequest) {
        bookService.updateBook(bookRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookByID(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }
    @GetMapping("/")
    public ResponseEntity<List<BookResponse>> getBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }

    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file")MultipartFile file){
        log.info("Size: {}",file.getSize());
        log.info("File Name: {}",file.getOriginalFilename());
    }
}
