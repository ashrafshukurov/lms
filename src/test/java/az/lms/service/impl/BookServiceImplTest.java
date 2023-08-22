package az.lms.service.impl;

import az.lms.dto.request.BookRequest;
import az.lms.mapper.AuthorMapper;
import az.lms.mapper.BookMapper;
import az.lms.mapper.CategoryMapper;
import az.lms.model.Book;
import az.lms.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.UUID;


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


    @Test
    public void createBook_WithValidInput_ShouldUploadFileAndSaveBook()  throws IOException {
        //arrange
        BookRequest bookRequest=new BookRequest();
        MultipartFile imageFile=new MockMultipartFile("image.jpg",new byte[0]);
        String fakeFileName= UUID.randomUUID().toString().substring(0,4)+"-image.jpg";
        Book fakeBook=new Book();
        fakeBook.setIsbn("12345");
        fakeBook.setImage(fakeFileName);
        Mockito.when(bookMapper.requestToEntity(bookRequest)).thenReturn(fakeBook);
        Mockito.when(bookRepository.existsByIsbn(fakeBook.getIsbn())).thenReturn(false);
        //act
        bookService.createBook(bookRequest,imageFile);

        //assert
        Mockito.verify(bookMapper).requestToEntity(bookRequest);
        Mockito.verify(bookRepository).existsByIsbn(fakeBook.getIsbn());
        Mockito.verify(bookRepository).save(fakeBook);

    }

}