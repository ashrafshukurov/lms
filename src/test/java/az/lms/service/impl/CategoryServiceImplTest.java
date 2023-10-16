/*
 *Created by Jaweed.Hajiyev
 *Date:16.10.23
 *TIME:12:13
 *Project name:LMS
 */

package az.lms.service.impl;

import az.lms.dto.request.CategoryRequest;
import az.lms.dto.response.CategoryResponse;
import az.lms.exception.AlreadyExistsException;
import az.lms.exception.NotFoundException;
import az.lms.mapper.CategoryMapper;
import az.lms.model.Category;
import az.lms.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {
    @InjectMocks
    CategoryServiceImpl service;
    @Mock
    CategoryRepository repository;

    @Mock
    CategoryMapper mapper;

    //  List<Book> books=new ArrayList<>(new Book())
    Category category = Category.builder()
            .id(1L)
            .name("Test name")
            .description("Test description")
            .type("Test type")
            .build();
    CategoryRequest request = CategoryRequest.builder()
            .name("Test category")
            .type("Test type")
            .description("Test description")
            .build();

    CategoryResponse response = CategoryResponse.builder()
            .name("Test category")
            .type("Test type")
            .description("Test description")
            .build();

    @Test
    void givenCreateCategoryIfNotExistByNameThenCreate() {
        //arrange
        when(repository.existsByName(request.getName())).thenReturn(false);
        when(mapper.requestToModel(request)).thenReturn(category);
        //act
        service.createCategory(request);
        //assert
        verify(repository, times(1)).save(category);
        verify(mapper, times(1)).requestToModel(request);
    }

    @Test
    void givenCreateCategoryIfExistByNameThenThrowException() {
        //arrange
        when(repository.existsByName(request.getName())).thenReturn(true);
        //act & assert

        assertThrows(AlreadyExistsException.class, () -> service.createCategory(request));
        verify(repository, times(1)).existsByName(request.getName());
        verify(repository, never()).save(category);
        verify(mapper, never()).requestToModel(request);
    }

    @Test
    void givenGetAllCategoryWhenFoundThenReturnCategoryList() {
        //arrange
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        when(repository.findAll()).thenReturn(categoryList);
        when(mapper.modelToResponse(category)).thenReturn(response);
        //act
        List<CategoryResponse> responses = service.getAllCategory();
        //assert
        verify(repository, times(1)).findAll();
        verify(mapper, times(categoryList.size())).modelToResponse(category);
        assertEquals(1, responses.size());
    }

    @Test
    void givenGetCategoryByIdWhenFoundThenReturnCategory() {
        //arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(category));
        when(mapper.modelToResponse(category)).thenReturn(response);
        //act
        CategoryResponse response1 = service.getCategoryById(id);
        //assert
        assertEquals("Test category", response1.getName());
        verify(repository, times(1)).findById(id);
        verify(mapper, times(1)).modelToResponse(category);
    }

    @Test
    void givenGetCategoryByIdWhenNotFoundThenThrowException() {
        //arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        //act & assert
        assertThrows(NotFoundException.class, () -> service.getCategoryById(id));
        verify(repository, times(1)).findById(id);
        verify(mapper, never()).modelToResponse(category);
    }

    @Test
    void givenUpdateCategoryWhenFoundThenUpdate() {
        //arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(category));
        when(mapper.requestToModel(request)).thenReturn(category);
        when(repository.save(category)).thenReturn(category);
        //act
        service.updateCategory(id, request);
        //assert
        verify(repository, times(1)).findById(id);
        verify(mapper, times(1)).requestToModel(request);
        verify(repository, times(1)).save(category);
    }

    @Test
    void givenUpdateCategoryWhenNotFoundThenThrowException() {
        //arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        //act & assert
        assertThrows(NotFoundException.class, () -> service.deleteCategoryById(id));
        verify(repository, times(1)).findById(id);
        verify(mapper, never()).modelToResponse(category);
    }

    @Test
    void givenDeleteCategoryByIdWhenIdIsPresentThenDelete() {
        //arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(category));
        //act
        service.deleteCategoryById(id);
        //assert
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).delete(category);
    }

    @Test
    void givenDeleteCategoryByIdWhenIdIsNotPresentThenThrowException() {
        //arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        //act & assert
        assertThrows(NotFoundException.class, () -> service.deleteCategoryById(id));
        verify(repository, times(1)).findById(id);
    }

    @Test
    void getBooksByCategory() {
        //arrange

        //act

        //assert
    }
}
