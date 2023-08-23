package az.lms.service.impl;

import az.lms.dto.request.BookRequest;
import az.lms.dto.response.BookResponse;
import az.lms.exception.NotFoundException;
import az.lms.mapper.AuthorMapper;
import az.lms.mapper.BookMapper;
import az.lms.mapper.CategoryMapper;
import az.lms.model.Book;
import az.lms.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    @Mock
    private CategoryMapper categoryMapper;

    @BeforeEach
    public void setUp() {
        book.setId(1L);
        book.setCount(4);
        book.setDescription("description");
        book.setIsbn("12345F");
    }


    @Test
    public void createBook_WithValidInput_ShouldUploadFileAndSaveBook() throws IOException {
        //arrange
        BookRequest bookRequest = new BookRequest();
        MultipartFile imageFile = new MockMultipartFile("image.jpg", new byte[0]);
        String fakeFileName = UUID.randomUUID().toString().substring(0, 4) + "-image.jpg";
        Book fakeBook = new Book();
        fakeBook.setIsbn("12345");
        fakeBook.setImage(fakeFileName);
        Mockito.when(bookMapper.requestToEntity(bookRequest)).thenReturn(fakeBook);
        Mockito.when(bookRepository.existsByIsbn(fakeBook.getIsbn())).thenReturn(false);
        //act
        bookService.createBook(bookRequest, imageFile);

        //assert
        Mockito.verify(bookMapper).requestToEntity(bookRequest);
        Mockito.verify(bookRepository).existsByIsbn(fakeBook.getIsbn());
        Mockito.verify(bookRepository).save(fakeBook);

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
        Mockito.verify(bookRepository, times(1)).findById(id);
        Mockito.verify(bookRepository, times(1)).delete(book1);
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
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setIsbn(book.getIsbn());
        bookResponse.setCount(book.getCount());
        bookResponse.setDescription(book.getDescription());

        when(bookRepository.findAll()).thenReturn(List.of(book));
        when(bookMapper.entityToResponse(book)).thenReturn(bookResponse);
        //act
        List<BookResponse> actualResponses = bookService.getAllBooks();
        //assert
        BookResponse bookResponse1 = actualResponses.get(0);
        assertEquals(bookResponse.getId(), bookResponse1.getId());
        assertEquals(bookResponse.getIsbn(), bookResponse1.getIsbn());
        assertEquals(bookResponse.getCount(),bookResponse1.getCount());
        assertEquals(bookResponse.getDescription(),bookResponse1.getDescription());
    }
    @Test
    public void givenGetAllBooksWhenFoundThenReturnEmptyList(){
        //arrange
        List<Book> books=new ArrayList<>();
        when(bookRepository.findAll()).thenReturn(books);
        //act
        List<BookResponse> bookResponses=bookService.getAllBooks();
        //assert
        assertNotNull(bookResponses);
        assertTrue(bookResponses.isEmpty());
    }

}