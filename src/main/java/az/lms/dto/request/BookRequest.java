package az.lms.dto.request;

<<<<<<< HEAD
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
=======
import lombok.*;

import java.time.LocalDate;
>>>>>>> main

import java.time.LocalDate;

/**
 * @author ashraf
 * @project LMS
 */
@Setter
@Getter
@Builder
<<<<<<< HEAD
=======
@NoArgsConstructor
@AllArgsConstructor
>>>>>>> main
public class BookRequest {
    private String name;
    private String isbn;
    private int count;
    private String image;
    private String authorName;
    private LocalDate publishedTime;

}
