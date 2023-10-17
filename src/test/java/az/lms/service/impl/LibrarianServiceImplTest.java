package az.lms.service.impl;

import az.lms.dto.request.LibrarianRequest;
import az.lms.dto.response.CategoryResponse;
import az.lms.dto.response.LibrarianResponse;
import az.lms.enums.RoleType;
import az.lms.exception.AlreadyExistsException;
import az.lms.exception.NotFoundException;
import az.lms.mapper.LibrarianMapper;
import az.lms.model.Category;
import az.lms.model.Librarian;
import az.lms.repository.LibrarianRepository;
import az.lms.security.PasswordCoderConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibrarianServiceImplTest {

    @InjectMocks
    LibrarianServiceImpl service;
    @Mock
    LibrarianRepository repository;
    @Mock
    LibrarianMapper mapper;
    @Mock
    PasswordCoderConfig passwordCoderConfig;
    Long id = 1L;
    Librarian librarian = Librarian.builder()
            .id(1L)
            .email("Librarian")
            .surname("LibrarianSurname")
            .roleType(RoleType.LIBRARIAN)
            .email("librarian@gmail.com")
            .password("Aa123123")
            .build();

    LibrarianRequest request = LibrarianRequest.builder()
            .email("Librarian")
            .surname("LibrarianSurname")
            .roleType(RoleType.LIBRARIAN)
            .email("librarian@gmail.com")
            .password("Aa123123")
            .build();

    LibrarianResponse response = LibrarianResponse.builder()
            .email("Librarian")
            .surname("LibrarianSurname")
            .roleType(RoleType.LIBRARIAN)
            .email("librarian@gmail.com")
            .build();

    @Test
    void givenCreateLibrarianWhenCreatedThenCreateLibrarian() {
        //arrange
        when(repository.existsByEmail(request.getEmail())).thenReturn(false);
        when(repository.save(librarian)).thenReturn(librarian);
        when(mapper.requestToModel(request)).thenReturn(librarian);
        //act
        service.createLibrarian(request);
        //assert
        verify(repository, times(1)).existsByEmail(request.getEmail());
        verify(repository, times(1)).save(librarian);
        verify(mapper, times(1)).requestToModel(request);
    }

    @Test
    void givenCreateLibrarianIfExistByEmailThenThrow() {
        //arrange
        when(repository.existsByEmail(request.getEmail())).thenReturn(true);
        //act & assert

        assertThrows(AlreadyExistsException.class, () -> service.createLibrarian(request));
        verify(repository, times(1)).existsByEmail(request.getEmail());
        verify(repository, never()).save(librarian);
        verify(mapper, never()).requestToModel(request);
    }

    @Test
    void givenGetAllLibrarianWhenFoundThenReturnAllLibrarian() {
        //arrange
        List<Librarian> librarianList = new ArrayList<>();
        librarianList.add(librarian);
        when(repository.findAll()).thenReturn(librarianList);
        when(mapper.modelToResponse(librarian)).thenReturn(response);
        //act
        List<LibrarianResponse> responses = service.getAllLibrarian();
        //assert
        verify(repository, times(1)).findAll();
        verify(mapper, times(librarianList.size())).modelToResponse(librarian);
        assertEquals(1, responses.size());
    }

    @Test
    void givenDeleteLibrarianWhenFoundThenDeleteLibrarian() {
        //arrange
        when(repository.findById(id)).thenReturn(Optional.of(librarian));
        //act
        service.deleteLibrarian(id);
        //assert
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).delete(librarian);
    }

    @Test
    void givenDeleteLibrarianWhenNotFoundThenThrowException() {
        //arrange
        when(repository.findById(id)).thenReturn(Optional.empty());
        //act & assert
        assertThrows(NotFoundException.class, () -> service.deleteLibrarian(id));
        verify(repository, times(1)).findById(id);
    }

    @Test
    void givenGetLibrarianByIdWhenFoundThenReturnLibrarian() {
        //arrange
        when(repository.findById(id)).thenReturn(Optional.of(librarian));
        when(mapper.modelToResponse(librarian)).thenReturn(response);
        //act
        LibrarianResponse response1 = service.getLibrarianById(id);
        //assert
        //assertEquals("Librarian", response1.getName());
        verify(repository, times(1)).findById(id);
        verify(mapper, times(1)).modelToResponse(librarian);
    }

    @Test
    void givenGetLibrarianByIdWhenNotFoundThenThrowException() {
        //arrange
        when(repository.findById(id)).thenReturn(Optional.empty());
        //act & assert
        assertThrows(NotFoundException.class, () -> service.getLibrarianById(id));
        verify(repository, times(1)).findById(id);
        verify(mapper, never()).modelToResponse(librarian);
    }

    @Test
    void givenUpdateLibrarianWhenFoundThenUpdateLibrarian() {
        //arrange
        when(repository.findById(id)).thenReturn(Optional.of(librarian));
        when(mapper.requestToModel(request)).thenReturn(librarian);
        when(repository.save(librarian)).thenReturn(librarian);
        //act
        service.updateLibrarian(id, request);
        //assert
        verify(repository, times(1)).findById(id);
        verify(mapper, times(1)).requestToModel(request);
        verify(repository, times(1)).save(librarian);
    }

    @Test
    void givenUpdateLibrarianWhenNotFoundThenThrowException() {
        //arrange
        when(repository.findById(id)).thenReturn(Optional.empty());
        //act & assert
        assertThrows(NotFoundException.class, () -> service.updateLibrarian(id, request));
        verify(repository, times(1)).findById(id);
        verify(mapper, never()).requestToModel(request);
    }
}