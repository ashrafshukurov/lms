/*
 *Created by Jaweed.Hajiyev
 *Date:12.08.23
 *TIME:01:46
 *Project name:LMS
 */

package az.lms.dto.response;

import az.lms.model.Book;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponse {
    private Long id;

    private String email;

    private String name;

    private String surname;

    private String biography;

    private LocalDate birthDay;

    private Set<Book> books;
}
