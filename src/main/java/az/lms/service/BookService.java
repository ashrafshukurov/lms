package az.lms.service;

import az.lms.dto.request.BookRequest;
import az.lms.dto.response.BookResponse;
import az.lms.dto.response.CategoryResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author ashraf
 * @project LMS
 */

public interface BookService {
    void createBook(BookRequest bookRequest, MultipartFile imageFileName) throws IOException;
    List<BookResponse> getAllBooks();
    void deleteBook(Long id);
    BookResponse getBookById(Long id);
    void updateBook(BookRequest bookRequest);
    CategoryResponse showCategoriesByBook(Long bookId);
    void uploadFile(MultipartFile multipartFile) throws IOException;
}
