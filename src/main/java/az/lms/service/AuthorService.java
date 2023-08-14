/*
 *Created by Jaweed.Hajiyev
 *Date:12.08.23
 *TIME:01:22
 *Project name:LMS
 */

package az.lms.service;

import az.lms.dto.request.AuthorRequest;
import az.lms.dto.request.BookRequest;
import az.lms.dto.response.AuthorResponse;
import az.lms.dto.response.BookResponse;
import az.lms.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AuthorService {
    void createAuthor(AuthorRequest request);

    List<AuthorResponse> getAllAuthors();

    void deleteAuthor(Long id);

    AuthorResponse getAuthorById(Long id);

    void updateAuthors(Long id, AuthorRequest request);

    public List<Book> getAuthorsByBook(Long authorId);
}
