/*
 *Created by Jaweed.Hajiyev
 *Date:12.08.23
 *TIME:01:22
 *Project name:LMS
 */

package az.lms.service.impl;

import az.lms.dto.request.AuthorRequest;
import az.lms.dto.response.AuthorResponse;
import az.lms.exception.NotFoundException;
import az.lms.mapper.AuthorMapper;
import az.lms.model.Author;
import az.lms.model.Book;
import az.lms.repository.AuthorRepository;
import az.lms.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;
    private final AuthorMapper mapper;

    @Override
    public void createAuthor(AuthorRequest request) {
        repository.save(mapper.requestToModel(request));
    }

    @Override
    public List<AuthorResponse> getAllAuthors() {
        List<Author> authors = repository.findAll();
        List<AuthorResponse> responses = new ArrayList<>();
        for (var a : authors) {
            responses.add(mapper.modelToResponse(a));
        }
        return responses;
    }

    @Override
    public void deleteAuthor(Long id) {
        repository.deleteById(id);
    }

    @Override
    public AuthorResponse getAuthorById(Long id) {
        Author author = repository.findById(id).orElseThrow(()
                -> new NotFoundException("Author not found!"));
        return mapper.modelToResponse(author);
    }

    @Override
    public void updateAuthors(Long id, AuthorRequest request) {
        Author author = repository.findById(id).orElseThrow(() -> new NotFoundException("Author not found"));
        if (request.getName() != null) {
            author.setName(request.getName());
        }
        if (request.getSurname() != null) {
            author.setSurname(request.getSurname());
        }
        if (request.getBiography() != null) {
            author.setBiography(request.getBiography());
        }
        if (request.getBirthDay() != null) {
            author.setBirthDay(request.getBirthDay());
        }
        if (request.getBooks() != null) {
            author.setBooks(request.getBooks());
        }
        repository.save(author);
    }

    @Override
    public List<Book> getAuthorsByBook(Long authorId) {
        Author author = repository.findById(authorId).orElseThrow(() -> new NotFoundException("Author not found"));
        return new ArrayList<>(author.getBooks());
    }
}
