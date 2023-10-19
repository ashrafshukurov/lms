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

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public void createAuthor(AuthorRequest request) {
            Author author = authorMapper.requestToModel(request);
            authorRepository.save(author);
            log.info("Created new author \n" + author);
    }

    @Override
    public List<AuthorResponse> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorResponse> responses = new ArrayList<>();
        for (var a : authors) {
            responses.add(authorMapper.modelToResponse(a));
        }
        log.info("Getting all authors.All author`s count {}", responses.size());
        return responses;
    }

    @Override
    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author not found with id " + id));
        authorRepository.delete(author);
        log.info("Author has been deleted successfully.Deleted author id {}", id);
    }

    @Override
    public AuthorResponse getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(()
                -> new NotFoundException("Author not found!"));
        log.info("Getting author by id" + author.toString());
        return authorMapper.modelToResponse(author);
    }

    @Override
    public void updateAuthors(Long id, AuthorRequest request) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author not found"));
        Author newAuthor = authorMapper.requestToModel(request);
        newAuthor.setId(author.getId());
        authorRepository.save(newAuthor);
        log.info("Author updated successfully");
    }

    @Override
    public List<Book> getBooksByAuthorId(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException("Author not found"));
        log.info("Getting books by author id.ID: {} Book count: {}", authorId, author.getBooks().size());
        return new ArrayList<>(author.getBooks());
    }
}
