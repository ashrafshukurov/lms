/*
 *Created by Jaweed.Hajiyev
 *Date:12.08.23
 *TIME:01:22
 *Project name:LMS
 */

package az.lms.service;

import az.lms.dto.request.AuthorRequest;
import az.lms.dto.response.AuthorResponse;
import az.lms.model.Book;

import java.util.List;

public interface AuthorService {
    void createAuthor(AuthorRequest request);
    List<AuthorResponse> getAllAuthors();
    void deleteAuthor(Long id);
    AuthorResponse getAuthorById(Long id);
    void updateAuthors(Long id, AuthorRequest request);
    List<Book> getBooksByAuthorId(Long authorId);
}
