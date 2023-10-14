/*
 *Created by Jaweed.Hajiyev
 *Date:13.10.23
 *TIME:17:12
 *Project name:LMS
 */

package az.lms.mapper.impl;

import az.lms.dto.request.AuthorRequest;
import az.lms.dto.response.AuthorResponse;
import az.lms.enums.RoleType;
import az.lms.mapper.AuthorMapper;
import az.lms.model.Author;
import az.lms.security.PasswordCoderConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorMapperImpl implements AuthorMapper {
    private final PasswordCoderConfig passwordCoderConfig;

    @Override
    public AuthorResponse modelToResponse(Author author) {
        AuthorResponse response = new AuthorResponse();
        response.setId(author.getId());
        response.setName(author.getName());
        response.setSurname(author.getSurname());
        response.setEmail(author.getEmail());
        response.setBiography(author.getBiography());
        response.setBirthDay(author.getBirthDay());
        response.setBooks(author.getBooks());
        return response;
    }

    @Override
    public Author requestToModel(AuthorRequest request) {
        Author author = new Author();
        author.setRoleType(RoleType.AUTHOR);
        author.setName(request.getSurname());
        author.setSurname(request.getSurname());
        author.setPassword(passwordCoderConfig.passwordEncode(request.getPassword()));
        author.setBiography(request.getBiography());
        author.setEmail(request.getEmail());
        return author;
    }
}
