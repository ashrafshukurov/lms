/*
 *Created by Jaweed.Hajiyev
 *Date:12.08.23
 *TIME:01:22
 *Project name:LMS
 */

package az.lms.service.impl;

import az.lms.dto.request.AuthorRequest;
import az.lms.dto.response.AuthorResponse;
import az.lms.enums.RoleType;
import az.lms.exception.AlreadyExistsException;
import az.lms.exception.NotFoundException;
import az.lms.mapper.AuthorMapper;
import az.lms.model.Author;
import az.lms.model.Book;
import az.lms.repository.AuthorRepository;
import az.lms.security.PasswordCoderConfig;
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
    private final PasswordCoderConfig passwordCoderConfig;

    @Override
    public void createAuthor(AuthorRequest request) {
        if (!repository.existsByEmail(request.getEmail())) {
            Author author = mapper.requestToModel(request);
            author.setRoleType(RoleType.AUTHOR);
            author.setPassword(passwordCoderConfig.passwordEncode(request.getPassword()));
            repository.save(author);
            log.info("Created new author \n" + author);
        } else {
            log.error("Author already exist!!!");
            throw new AlreadyExistsException("Author already exist!!!");
        }
    }

    @Override
    public List<AuthorResponse> getAllAuthors() {
        List<Author> authors = repository.findAll();
        List<AuthorResponse> responses = new ArrayList<>();
        for (var a : authors) {
            responses.add(mapper.modelToResponse(a));
        }
        log.info("Getting all authors.All author`s count {}", responses.size());
        return responses;
    }

    @Override
    public void deleteAuthor(Long id) {
        repository.deleteById(id);
        log.info("Author has been deleted successfully.Deleted author id {}", id);
    }

    @Override
    public AuthorResponse getAuthorById(Long id) {
        Author author = repository.findById(id).orElseThrow(()
                -> new NotFoundException("Author not found!"));
        log.info("Getting author by id" + author.toString());
        return mapper.modelToResponse(author);
    }

    @Override
    public void updateAuthors(Long id, AuthorRequest request) {
        Author author = repository.findById(id).orElseThrow(() -> new NotFoundException("Author not found"));
        if (request.getName() != null) {
            author.setName(request.getName());
            log.info("Author name updated.");
        }
        if (request.getSurname() != null) {
            author.setSurname(request.getSurname());
            log.info("Author surname updated.");
        }
        if (request.getBiography() != null) {
            author.setBiography(request.getBiography());
            log.info("Author biography updated.");
        }
        if (request.getBirthDay() != null) {
            author.setBirthDay(request.getBirthDay());
            log.info("Author birthday updated.");
        }
            repository.save(author);
        log.info("Author updated successfully");
    }

    @Override
    public List<Book> getBooksByAuthorId(Long authorId) {
        Author author = repository.findById(authorId).orElseThrow(() -> new NotFoundException("Author not found"));
        log.info("Getting books by author id.ID: {} Book count: {}", authorId, author.getBooks().size());
        return new ArrayList<>(author.getBooks());
    }
}
