package az.lms.controller;

import az.lms.dto.request.BookRequest;
import az.lms.dto.response.BookResponse;
import az.lms.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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


//    @PostMapping("/add")
//    public void addBook(
//            @RequestParam MultipartFile file,
//            @RequestParam String name,
//            @RequestParam String isbn,
//            @RequestParam int count,
//            @RequestParam String authorName,
//            @RequestParam Long categoriesId
//    ) throws IOException {
//        BookRequest bookRequest = new BookRequest();
//        bookRequest.setName(name);
//        bookRequest.setIsbn(isbn);
//        bookRequest.setCount(count);
//        bookRequest.setAuthorName(authorName);
//        bookRequest.setCategoriesId(categoriesId);
//        bookService.createBook(bookRequest, file);
//    }
    @PostMapping("/add")
    public void addBook(@RequestBody MultipartFile file,BookRequest bookRequest) throws IOException {
        bookService.createBook(bookRequest, file);
    }



    @PutMapping("/update")
    public void updateBook(@RequestBody BookRequest bookRequest) {
        bookService.updateBook(bookRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookByID(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookResponse>> getBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

//        @PostMapping("/upload")
//    public void upLoadFile(@RequestParam("file") MultipartFile file) throws IOException {
//        Path path = Paths.get(directory);
//        log.info("size:{}", file.getSize());
//        log.info("name:{}", file.getOriginalFilename());
//        Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()));
//    }
//    @GetMapping("/download")
//    public ResponseEntity<Resource> download(@RequestParam("file") String fileName) {
//        Path path = Paths.get(directory);
//        Resource resource = fileUtil.load(fileName, path);
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
//                .body(resource);
//    }

}
