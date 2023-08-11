package az.lms.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author ashraf
 * @project LMS
 */
@Setter
@Getter
@Builder
public class BookRequest {
    private Long id;
    private String name;
    private String isbn;
    private int count;
    private String image;
    private String authorName;
    private LocalDate publishedTime;

}
