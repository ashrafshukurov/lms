package az.lms.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishedTime;
    private Long categoriesId;
    private String description;

}
