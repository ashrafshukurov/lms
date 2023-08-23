/*
 *Created by Jaweed.Hajiyev
 *Date:12.08.23
 *TIME:01:46
 *Project name:LMS
 */

package az.lms.dto.request;

import az.lms.model.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequest {
    @NotBlank(message = "Name cannot be empty")
    @Size(max = 25, min = 2, message = "Invalid Name: Must be of 2 - 25 characters")
    private String name;

    @Size(max = 25, message = "Invalid Name: Must be of 3 - 30 characters")
    private String surname;

    @Size(max = 200, message = "Invalid biography: Must be of max 200 characters")
    private String biography;

    @NotNull(message = "Invalid birthday: Birthday is NULL")
    @Past(message = "Date should not be in the future")
    private LocalDate birthDay;


    private Set<Book> books;


}
