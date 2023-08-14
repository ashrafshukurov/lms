/*
 *Created by Jaweed.Hajiyev
 *Date:13.08.23
 *TIME:23:15
 *Project name:LMS
 */

package az.lms.mapper;

import az.lms.dto.request.CategoryRequest;
import az.lms.dto.response.CategoryResponse;
import az.lms.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse modelToResponse(Category author);
    Category requestToModel(CategoryRequest request);
}
