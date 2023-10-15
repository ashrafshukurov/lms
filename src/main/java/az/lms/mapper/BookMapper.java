package az.lms.mapper;

import az.lms.dto.request.BookRequest;
import az.lms.dto.response.BookResponse;
import az.lms.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @author ashraf
 * @project LMS
 */

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
    @Mapping(target = "categories.id", source = "categories_id")
    Book requestToEntity(BookRequest request);

    BookResponse entityToResponse(Book book);
}
