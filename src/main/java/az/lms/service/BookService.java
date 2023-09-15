package az.lms.service;

import az.lms.dto.request.BookRequest;
import az.lms.dto.response.BookResponse;
import az.lms.dto.response.CategoryResponse;
import az.lms.model.Book;
import az.lms.model.Category;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author ashraf
 * @project LMS
 */

public interface BookService {
    String createBook(BookRequest bookRequest, MultipartFile imageFileName) throws IOException;
    List<BookResponse> getAllBooks();
    String deleteBook(Long id);
    BookResponse getBookById(Long id);
    String updateBook(BookRequest bookRequest);
    void uploadFile(MultipartFile multipartFile) throws IOException;
    BookResponse getBookByName(String bookName);

}
