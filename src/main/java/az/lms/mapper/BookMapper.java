package az.lms.mapper;

import az.lms.dto.request.BookRequest;
import az.lms.dto.response.BookResponse;
import az.lms.model.Book;
import org.mapstruct.Mapper;

/**
 * @author ashraf
 * @project LMS
 */
@Mapper(componentModel = "spring")
public interface BookMapper {
     Book requestToEntity(BookRequest request);
     BookResponse entityToResponse(Book book);
}
