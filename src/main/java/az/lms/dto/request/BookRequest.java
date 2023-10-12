package az.lms.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
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

    @NotBlank(message = "name can't be empty")
    private String name;
    @NotBlank(message = "isbn can't be empty")
    private String isbn;
    @Positive(message = "count can't be negative value")
    private int count;
    private String image;
    @NotBlank(message = "details shouldn't be empty")
    private String details;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Past(message = "publishedTime should not be in the future")
    private LocalDate publishedTime;
    @NotNull
    private Long categories_id;
    @NotBlank(message = "description can't be empty")
    @Size(max = 25, min = 5)
    private String description;
    private Long author_id;

}
