package az.lms.service.impl;

import az.lms.dto.request.BookRequest;
import az.lms.dto.response.AuthorResponse;
import az.lms.dto.response.BookResponse;

import az.lms.exception.AlreadyExistsException;
import az.lms.exception.NotFoundException;
import az.lms.mapper.AuthorMapper;
import az.lms.mapper.BookMapper;
import az.lms.model.Author;
import az.lms.model.Book;
import az.lms.model.Category;
import az.lms.repository.AuthorRepository;
import az.lms.repository.BookRepository;
import az.lms.service.BookService;
import az.lms.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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
    private final FileUtil fileUtil;
    private final AuthorRepository authorRepository;
    @Override
    public void createBook(BookRequest bookRequest, MultipartFile imageFile) throws IOException {
        final String bucketName = "library.management.system";
        log.info("uploading file");
        Book book = bookMapper.requestToEntity(bookRequest);
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new AlreadyExistsException("Book with ISBN " + book.getIsbn() + " already exists");
        }
        Author author = authorRepository.findById(bookRequest.getAuthor_id()).orElseThrow(()->new NotFoundException("Author is not found with this id"+bookRequest.getAuthor_id()));
        author.addBook(book);
        String region = "eu-central-1";
        String objectKey = "images/" + imageFile.getOriginalFilename();
        String s3ObjectUrl = "https://s3." + region + ".amazonaws.com/" + bucketName + "/" + objectKey;
        book.setImage(s3ObjectUrl);
        fileUtil.uploadFile(bucketName, objectKey, imageFile);
        log.info("creating book");
        bookRepository.save(book);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        log.info("getting all books");
        List<Book> books = bookRepository.findAll();
        List<Category> categories = new ArrayList<>();
        List<Set<Author>> authorsList = new ArrayList<>();
        books.forEach(book -> {
            categories.add(book.getCategories());
            authorsList.add(book.getAuthors());
        });
        List<BookResponse> bookResponses = books.stream()
                .map(bookMapper::entityToResponse)
                .toList();
        IntStream.range(0, bookResponses.size()).forEach(i -> {
            BookResponse bookResponse = bookResponses.get(i);
            bookResponse.setCategory(categories.get(i).getName());
            Set<Author> authors = authorsList.get(i);
            List<String> authorsName = authors.stream()
                    .map(author -> author.getName() + " " + author.getSurname())
                    .collect(Collectors.toList());
            bookResponse.setAuthorsName(authorsName);
        });
        return bookResponses;
    }

    @Transactional
    @Override
    public void deleteBook(Long id) throws NotFoundException {

        log.info("deleting book");
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book with ID " + id + " not found"));

        bookRepository.delete(book);
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
        List<String> authorsName = authorResponseSet.stream().map(authorResponse -> authorResponse.getName() + " " + authorResponse.getSurname()).collect(Collectors.toList());
        bookResponse.setAuthorsName(authorsName);
        return bookResponse;
    }

    @Override
    public void updateBook(BookRequest bookRequest) {
        Book book = bookRepository.findByIsbn(bookRequest.getIsbn()).orElseThrow(() -> new NotFoundException("invalid book"));
        Book newBook = bookMapper.requestToEntity(bookRequest);
        newBook.setId(book.getId());
        newBook.setIsbn(book.getIsbn());
        newBook.setCategories(book.getCategories());
        bookRepository.save(newBook);
    }

    @Override
    public List<BookResponse> getBookByName(String bookName) {
        List<Book> books = bookRepository.getBookByName(bookName)
                .orElseThrow(() -> new NotFoundException("Not found book with this name: " + bookName));
        List<BookResponse> bookResponses = new ArrayList<>();
        books.forEach(book -> {
            BookResponse bookResponse = bookMapper.entityToResponse(book);
            Category category = book.getCategories();
            bookResponse.setCategory(category.getName());
            List<String> authorsName = book.getAuthors().stream()
                    .map(authorMapper::modelToResponse)
                    .map(authorResponse -> authorResponse.getName() + " " + authorResponse.getSurname())
                    .collect(Collectors.toList());
            bookResponse.setAuthorsName(authorsName);
            bookResponses.add(bookResponse);
        });
        return bookResponses;
    }


}
