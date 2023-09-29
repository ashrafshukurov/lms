package az.lms.service.impl;

import az.lms.dto.request.BookRequest;
import az.lms.dto.response.AuthorResponse;
import az.lms.dto.response.BookResponse;
import az.lms.exception.AlreadyExistsException;
import az.lms.exception.NotFoundException;
import az.lms.mapper.AuthorMapper;
import az.lms.mapper.BookMapper;
import az.lms.mapper.CategoryMapper;
import az.lms.model.Author;
import az.lms.model.Book;
import az.lms.model.Category;
import az.lms.repository.BookRepository;
import az.lms.util.FileUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 * @author ashraf
 * @project LMS
 */
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl bookService;
    @InjectMocks
    private Book book;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @Mock
    private AuthorMapper authorMapper;
    @InjectMocks
    private Category category;
    @InjectMocks
    private Author author;
    @Mock
    private FileUtil fileUtil;

    @BeforeEach
    public void setBook() {
        book.setId(1L);
        book.setCount(4);
        book.setDescription("description");
        book.setIsbn("12345F");
        book.setName("book");

    }

    @BeforeEach
    public void setCategory() {
        category.setId(1L);
        category.setName("Category1");
        category.setDescription("description");
    }

    @BeforeEach
    public void setAuthor() {
        author.setId(2L);
        author.setSurname("Authorov");
        author.setName("Author");
        author.setBiography("bio");
    }


    @Test
    public void createBook_WithValidInput_ShouldUploadFileAndSaveBook() throws IOException {
        //arrange
        MultipartFile imageFile = new MockMultipartFile("image.jpg", new byte[0]);
        String fakeFileName = UUID.randomUUID().toString().substring(0, 4) + "image.jpg";
        BookRequest bookRequest = new BookRequest();
        bookRequest.setIsbn("1234f");
        bookRequest.setName("book1");
        bookRequest.setImage(fakeFileName);
        bookRequest.setDetails("details");
        bookRequest.setCount(12);
        bookRequest.setDescription("desc");
        bookRequest.setCategories_id(1L);

        Book fakeBook = new Book();
        fakeBook.setId(1L);
        fakeBook.setIsbn("1234f");
        fakeBook.setImage(fakeFileName);
        Category category1=new Category();
        category1.setName("category");
        category1.setId(1L);
        fakeBook.setCategories(category1);
        fakeBook.setCount(12);
        when(bookMapper.requestToEntity(bookRequest)).thenReturn(fakeBook);
        when(bookRepository.existsByIsbn(fakeBook.getIsbn())).thenReturn(false);
        doNothing().when(fileUtil).uploadFile(anyString(),anyString(),imageFile);
        //act
        bookService.createBook(bookRequest, imageFile);

        //assert
        verify(bookMapper).requestToEntity(bookRequest);
        verify(bookRepository).existsByIsbn(fakeBook.getIsbn());
        verify(fileUtil).uploadFile(anyString(),anyString(),imageFile);
        verify(bookRepository).save(fakeBook);

    }

    @Test
    public void testCreateBookWithExistingISbn() {
        //arrange
        MultipartFile imageFile = new MockMultipartFile("imageFile", "test.jpg", "image/jpeg", "test".getBytes());
        BookRequest bookRequest1 = new BookRequest();
        String isbn="123f";
        bookRequest1.setIsbn(isbn);
        Book bookEntity=new Book();
        bookEntity.setId(1L);
        bookEntity.setDetails("Details");
        bookEntity.setIsbn("123f");
        Category category1=new Category();
        category1.setId(1L);
        bookEntity.setCategories(category1);

        book.setIsbn(isbn);
        when(bookMapper.requestToEntity(bookRequest1)).thenReturn(bookEntity);
        when(bookRepository.existsByIsbn(anyString())).thenReturn(true);

        // Act and Assert
        assertThrows(AlreadyExistsException.class, () -> bookService.createBook(bookRequest1, imageFile));
    }

    @Test
    public void givenDeleteBookByIdWhenFoundThenReturnResult() {
        //arrange
        Long id = 1L;
        Book book1 = new Book();
        book1.setId(id);
        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(book1));
        //act
        bookService.deleteBook(id);
        //assert
        verify(bookRepository, times(1)).findById(id);
        verify(bookRepository, times(1)).delete(book1);
    }

    @Test
    public void givenDeleteBookByIdWhenNotFoundThenThrow404() {
        //arrange
        Long invalidId = 1L;
        Mockito.when(bookRepository.findById(invalidId)).thenReturn(Optional.empty());
        //act & assert
        assertThrows(NotFoundException.class, () -> bookService.deleteBook(invalidId));
    }

    @Test
    public void givenGetAllBooksWhenFoundThenReturnBookList() {
        //arrange

        book.setCategories(category);
        Set<Author> authors = new HashSet<>();

        authors.add(author);
        book.setAuthors(authors);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setIsbn("1212F");
        book2.setCount(11);
        Category category2 = new Category();
        category2.setName("Category2");
        book2.setCategories(category2);
        Set<Author> authors2 = new HashSet<>();
        Author author2 = new Author();
        author2.setSurname("Authorov2");
        author2.setName("Author2");
        authors2.add(author2);
        book2.setAuthors(authors2);

        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book2);
        when(bookRepository.findAll()).thenReturn(books);


        BookResponse response1 = new BookResponse();
        response1.setId(1L);
        response1.setCategory("Category1");
        when(bookMapper.entityToResponse(book)).thenReturn(response1);

        BookResponse response2 = new BookResponse();
        response2.setId(2L);
        response2.setCategory("Category2");
        when(bookMapper.entityToResponse(book2)).thenReturn(response2);
        //act
        List<BookResponse> bookResponses = bookService.getAllBooks();
        //assert
        assertEquals(2, bookResponses.size());
        assertEquals("Category1", bookResponses.get(0).getCategory());
        assertEquals("Category2", bookResponses.get(1).getCategory());
        verify(bookRepository,times(1)).findAll();
        verify(bookMapper,times(2)).entityToResponse(any());

    }

    @Test
    public void givenGetAllBooksWhenFoundThenReturnEmptyList() {
        //arrange
        when(bookRepository.findAll()).thenReturn(Collections.emptyList());
        //act
        List<BookResponse> bookResponses = bookService.getAllBooks();
        //assert
        assertNotNull(bookResponses);
        assertTrue(bookResponses.isEmpty());
    }

    @Test
    public void givenGetBookByIdWhenFoundThenReturnResponse() {
        book.setCategories(category);
        Set<Author> authors = new HashSet<>();
        authors.add(author);
        book.setAuthors(authors);

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setCategory(book.getCategories().getName());

        when(bookMapper.entityToResponse(book)).thenReturn(bookResponse);

        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setName("Author");
        authorResponse.setSurname("Authorov");

        when(authorMapper.modelToResponse(any(Author.class))).thenReturn(authorResponse);
        //act
        BookResponse bookResponse1 = bookService.getBookById(book.getId());

        //assert
        assertNotNull(bookResponse1);
        assertEquals(book.getId(), bookResponse1.getId());
        assertEquals(category.getName(), bookResponse1.getCategory());
        verify(bookRepository,times(1)).findById(any());
        verify(bookMapper,times(1)).entityToResponse(any());
        verify(authorMapper,times(1)).modelToResponse(any());


    }

    @Test
    public void givenGetBookByIdWhenNotFoundThenThrow404() {
        //arrange
        Long invalidId = 100L;
        when(bookRepository.findById(invalidId)).thenReturn(Optional.empty());
        //act && Assert
        assertThrows(NotFoundException.class, () -> bookService.getBookById(invalidId));
    }

    @Test
    public void givenGetBookByNameWhenFoundThenReturnResponse() {
        //arrange
        book.setCategories(category);
        Set<Author> authors = new HashSet<>();
        authors.add(author);
        book.setAuthors(authors);
        when(bookRepository.getBookByName(book.getName())).thenReturn(Optional.of(List.of(book)));

        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setCategory(book.getCategories().getName());
        bookResponse.setName(book.getName());
        bookResponse.setIsbn(book.getIsbn());
        bookResponse.setCount(book.getCount());


        when(bookMapper.entityToResponse(book)).thenReturn(bookResponse);

        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setName("Author");
        authorResponse.setSurname("Authorov");

        when(authorMapper.modelToResponse(any(Author.class))).thenReturn(authorResponse);
        //act
        List<BookResponse> bookResponse1 = bookService.getBookByName(book.getName());

        //assert
        assertNotNull(bookResponse1);
        assertEquals(book.getId(), bookResponse1.get(0).getId());
        assertEquals(book.getName(), bookResponse1.get(0).getName());
        assertEquals(category.getName(), bookResponse1.get(0).getCategory());
        assertEquals(book.getIsbn(), bookResponse1.get(0).getIsbn());
        assertEquals(book.getCount(), bookResponse1.get(0).getCount());
        verify(bookRepository,times(1)).getBookByName(anyString());
        verify(bookMapper,times(1)).entityToResponse(any());
        verify(authorMapper,times(1)).modelToResponse(any());

    }

    @Test
    public void givenGetBookByNameWhenNotFoundThenThrow404() {
        //arrange
        String invalidName = "kjsjfs";
        when(bookRepository.getBookByName(invalidName)).thenReturn(Optional.empty());
        //assert & act
        assertThrows(NotFoundException.class, () -> bookService.getBookByName(invalidName));


    }

    @Test
    public void givenUpdateBookWhenFoundThenUpdate() {
        //arrange
        String isbn = "12345F";
        when(bookRepository.findByIsbn(isbn)).thenReturn(Optional.of(book));
        BookRequest bookRequest = new BookRequest();
        bookRequest.setCount(book.getCount());
        bookRequest.setName(book.getName());
        bookRequest.setImage(book.getImage());
        bookRequest.setDescription(book.getDescription());
        bookRequest.setCategories_id(category.getId());
        bookRequest.setIsbn(book.getIsbn());
        when(bookMapper.requestToEntity(bookRequest)).thenReturn(book);
        // Act
        assertDoesNotThrow(() -> bookService.updateBook(bookRequest));

        //assert
        assertNotNull(bookRequest);
        verify(bookRepository, times(1)).findByIsbn(isbn);
        verify(bookMapper, times(1)).requestToEntity(bookRequest);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void givenUpdateBookWhenNotFoundThenThrows404() {
        //arrange
        String invalidIsbn = "1234565432s";
        BookRequest bookRequest = new BookRequest();
        bookRequest.setIsbn(invalidIsbn);
        when(bookRepository.findByIsbn(invalidIsbn)).thenReturn(Optional.empty());
        //act & assert
        assertThrows(NotFoundException.class, () -> bookService.updateBook(bookRequest));
    }


}