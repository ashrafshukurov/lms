package az.lms.service;

import az.lms.dto.request.BookRequest;
import az.lms.dto.response.BookResponse;

import java.util.List;

/**
 * @author ashraf
 * @project LMS
 */

public interface BookService {
    void createBook(BookRequest bookRequest);
    List<BookResponse> getAllBooks();
    void deleteBook(Long id);
    BookResponse getBookById(Long id);
    void updateBook(BookRequest bookRequest);
}
