package az.lms.dto.response;


import lombok.*;


import java.io.File;
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
    private LocalDate publishedTime;
    private String category;
    private List<String> authorsName;
    private String description;
    private String details;

}
