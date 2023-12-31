/*
 *Created by Jaweed.Hajiyev
 *Date:22.08.23
 *TIME:16:37
 *Project name:LMS
 */

package az.lms.mapper;

import az.lms.dto.request.LibrarianRequest;
import az.lms.dto.response.LibrarianResponse;
import az.lms.model.Librarian;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LibrarianMapper {
    LibrarianResponse modelToResponse(Librarian author);
    Librarian requestToModel(LibrarianRequest request);
}
