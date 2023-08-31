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
    @Value("${file.directory}")
    private String directory;

    @Override
    public void createBook(BookRequest bookRequest, MultipartFile imageFile) throws IOException {
        log.info("uploading file");
        String fileName = UUID.randomUUID().toString().substring(0, 4) + "-" + imageFile.getOriginalFilename();
        Book book = bookMapper.requestToEntity(bookRequest);
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new AlreadyExistsException("Book with ISBN " + book.getIsbn() + " already exists");
        }
        book.setImage(fileName);
        uploadFile(imageFile);

        log.info("creating book");
        bookRepository.save(book);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        log.info("getting all books");
        List<Book> books = bookRepository.findAll();
        List<Category> categories = new ArrayList<>();
        List<Set<Author>> authorsList = new ArrayList<>();
        for (Book book : books) {
            categories.add(book.getCategories());
            authorsList.add(book.getAuthors());
        }
        List<BookResponse> bookResponses = books.stream()
                .map(bookMapper::entityToResponse)
                .toList();
        for (int i = 0; i < bookResponses.size(); i++) {
            BookResponse bookResponse = bookResponses.get(i);
            bookResponse.setCategory(categories.get(i).getName());

            Set<Author> authors = authorsList.get(i);
            List<String> authorsName = authors.stream()
                    .map(author -> author.getName() + " " + author.getSurname())
                    .collect(Collectors.toList());

            bookResponse.setAuthorsName(authorsName);
        }
        return bookResponses;
    }


    @Override
    public String deleteBook(Long id) throws NotFoundException {

        log.info("deleting book");
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book with ID " + id + " not found"));

        bookRepository.delete(book);
        return "Book is deleted";

    }


    @Override
    public BookResponse getBookById(Long id) throws NotFoundException {
        log.info("getting book by id:{}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book with ID " + id + " not found"));
        Category category = book.getCategories();
        BookResponse bookResponse = bookMapper.entityToResponse(book);
        bookResponse.setCategory(category.getName());
        final Set<Author> authors = book.getAuthors();
        Set<AuthorResponse> authorResponseSet = authors.stream().map(authorMapper::modelToResponse).collect(Collectors.toSet());
        List<String> authorsName = new ArrayList<>();
        for (AuthorResponse authorResponse : authorResponseSet) {
            authorsName.add(authorResponse.getName() + " " + authorResponse.getSurname());
        }
        bookResponse.setAuthorsName(authorsName);
        return bookResponse;

    }

    @Override
    public String updateBook(BookRequest bookRequest) {
        Book book = bookRepository.findByIsbn(bookRequest.getIsbn()).orElseThrow(() -> new NotFoundException("invalid book"));
        Book newBook = bookMapper.requestToEntity(bookRequest);
        newBook.setId(book.getId());
        newBook.setIsbn(book.getIsbn());
        newBook.setCategories(book.getCategories());
        bookRepository.save(newBook);
        return "Book is updated";
    }

    public void uploadFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString().substring(0, 4) + "-" + file.getOriginalFilename();
        Path filePath = Paths.get(directory).resolve(fileName);
        Files.copy(file.getInputStream(), filePath);
    }

    @Override
    public BookResponse getBookByName(String bookName) {
        Book book = bookRepository.getBookByName(bookName)
                .orElseThrow(() -> new NotFoundException("Not found book with this name: " + bookName));
        BookResponse bookResponse = bookMapper.entityToResponse(book);
        Category category=book.getCategories();
        bookResponse.setCategory(category.getName());
        List<String> authorsName = book.getAuthors().stream()
                .map(authorMapper::modelToResponse)
                .map(authorResponse -> authorResponse.getName() + " " + authorResponse.getSurname())
                .collect(Collectors.toList());
        bookResponse.setAuthorsName(authorsName);
        return bookResponse;
    }


}
