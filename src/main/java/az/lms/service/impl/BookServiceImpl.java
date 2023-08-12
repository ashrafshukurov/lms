package az.lms.service.impl;

import az.lms.dto.request.BookRequest;
import az.lms.dto.response.BookResponse;
import az.lms.mapper.BookMapper;
import az.lms.model.Book;
import az.lms.repository.BookRepository;
import az.lms.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author ashraf
 * @project LMS
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public void createBook(BookRequest bookRequest) {
       Book book=bookMapper.requestToEntity(bookRequest);
       bookRepository.save(book);
    }

    @Override
    public List<BookResponse> getAllBooks() {
       List<Book> books= bookRepository.findAll();
       return books.stream().map(bookMapper::entityToResponse).toList();
    }

    @Override
    public void deleteBook(Long id) {

    }

    @Override
    public BookResponse getBookById(Long id) {
       Book  book=bookRepository.findById(id).orElseThrow();
    }

    @Override
    public void updateBook(Long id, BookRequest bookRequest) {

    }
}
