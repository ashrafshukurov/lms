package az.lms.mapper;

import az.lms.dto.request.StudentRequest;
import az.lms.dto.response.StudentResponse;
import az.lms.model.Student;
import org.mapstruct.Mapper;

/**
 * @author Mehman Osmanov on 12.08.23
 * @project LMS
 */
@Mapper(componentModel = "spring")
public interface StudentMapper {
   Student requestToEntity(StudentRequest request);
   StudentResponse entityToResponse(Student student);

}
