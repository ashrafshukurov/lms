package az.lms.dto.response;

import az.lms.model.Author;
import az.lms.model.Category;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * @author ashraf
 * @project LMS
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {

    private Long id;
    private String name;
    private String isbn;
    private int count;
    private String image;
//    private MultipartFile multipartFile;
    private String authorName;
    private LocalDate publishedTime;
    private Category category;
    private List<Author> authors;





}
