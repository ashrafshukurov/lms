package az.lms.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.*;

import java.time.LocalDate;


import java.time.LocalDate;

/**
 * @author ashraf
 * @project LMS
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    private String name;
    private String isbn;
    private int count;
    private String image;
    private String authorName;
    private LocalDate publishedTime;

}
