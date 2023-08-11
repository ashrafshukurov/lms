package az.lms.dto.response;

import lombok.*;

import java.time.LocalDate;

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
    private String authorName;
    private LocalDate publishedTime;
}
