package az.lms.service.impl;

import az.lms.dto.request.BookRequest;
import az.lms.dto.response.AuthorResponse;
import az.lms.dto.response.BookResponse;

import az.lms.dto.response.CategoryResponse;
import az.lms.exception.AlreadyExistsException;
import az.lms.exception.NotFoundException;
import az.lms.mapper.AuthorMapper;
import az.lms.mapper.BookMapper;
import az.lms.mapper.CategoryMapper;
import az.lms.model.Author;
import az.lms.model.Book;
import az.lms.model.Category;
import az.lms.repository.BookRepository;
import az.lms.service.BookService;
import az.lms.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author ashraf
 * @project LMS
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;
    private final CategoryMapper categoryMapper;
    @Value("${file.directory}")
    private String directory;

    @Override
    public void createBook(BookRequest bookRequest, MultipartFile imageFile){
        log.info("uploading file");
        String fileName = UUID.randomUUID().toString().substring(0, 4) + "-" + imageFile.getOriginalFilename();

        Book book = bookMapper.requestToEntity(bookRequest);
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new AlreadyExistsException("Book with ISBN " + book.getIsbn() + " already exists");
        }
        log.info("creating book");
        bookRepository.save(book);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        log.info("getting all books");
        List<Book> books = bookRepository.findAll();
        return books.stream().map(bookMapper::entityToResponse).toList();
    }

    @Override
    public void deleteBook(Long id) throws NotFoundException {
        try {
            log.info("deleting book");
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
            log.info("getting book by id:{}", id);
            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Book with ID " + id + " not found"));
            Category category = book.getCategories();
            BookResponse bookResponse = bookMapper.entityToResponse(book);
//            bookResponse.setCategory(category);
//            final Set<Author> authors = book.getAuthors();
//
//            Set<AuthorResponse> authorResponseSet = authors.stream().map(authorMapper::modelToResponse).collect(Collectors.toSet());
//            bookResponse.setAuthors(authorResponseSet);

            return bookResponse;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch book with ID " + id, e);
        }
    }


    @Override
    public void updateBook(BookRequest bookRequest) {
        Book book = bookRepository.findByIsbn(bookRequest.getIsbn()).orElseThrow(() -> new NotFoundException("invalid book"));
        Book newBook = bookMapper.requestToEntity(bookRequest);
        newBook.setId(book.getId());
        bookRepository.save(newBook);
    }

    @Override
    public CategoryResponse showCategoriesByBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book with ID " + bookId + " not found"));
        Category category = book.getCategories();
        return categoryMapper.modelToResponse(category);

    }

    public void uploadFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString().substring(0,4) + "-" + file.getOriginalFilename();
        Path filePath = Paths.get(directory).resolve(fileName);
        Files.copy(file.getInputStream(), filePath);
    }


}
