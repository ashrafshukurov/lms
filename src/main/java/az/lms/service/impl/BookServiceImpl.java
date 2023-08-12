package az.lms.service.impl;

import az.lms.dto.request.BookRequest;
import az.lms.dto.response.BookResponse;

import az.lms.exception.AlreadyExistsException;
import az.lms.exception.NotFoundException;
import az.lms.mapper.BookMapper;
import az.lms.model.Book;
import az.lms.repository.BookRepository;
import az.lms.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


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
        Book book = bookMapper.requestToEntity(bookRequest);
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new AlreadyExistsException("Book with ISBN " + book.getIsbn() + " already exists");

        }
        bookRepository.save(book);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(bookMapper::entityToResponse).toList();
    }

    @Override
    public void deleteBook(Long id) throws NotFoundException {
        try {
            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Book with ID " + id + " not found"));

            bookRepository.delete(book);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete book with ID " + id, e);
        }
    }


    @Override
    public BookResponse getBookById(Long id) throws NotFoundException {
        try {
            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Book with ID " + id + " not found"));

            return bookMapper.entityToResponse(book);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch book with ID " + id, e);
        }
    }


    @Override
    public void updateBook(Long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book with ID " + id + " not found"));
        book = Book.builder()
                .authorName(bookRequest.getAuthorName())
                .count(bookRequest.getCount())
                .name(bookRequest.getName())
                .publishedTime(bookRequest.getPublishedTime())
                .isbn(bookRequest.getIsbn())
                .image(bookRequest.getImage()).build();
       bookRepository
               .save(book);
    }

}
