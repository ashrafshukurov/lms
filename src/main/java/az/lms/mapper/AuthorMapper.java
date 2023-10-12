/*
 *Created by Jaweed.Hajiyev
 *Date:12.08.23
 *TIME:01:54
 *Project name:LMS
 */

package az.lms.mapper;

import az.lms.dto.request.AuthorRequest;
import az.lms.dto.response.AuthorResponse;
import az.lms.model.Author;
import az.lms.security.PasswordCoderConfig;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorResponse modelToResponse(Author author);
    Author requestToModel(AuthorRequest request);

}
