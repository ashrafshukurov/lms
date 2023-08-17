package az.lms.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
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

    @NotBlank(message = "name can't be empty")
    private String name;
    @NotBlank(message = "isbn can't be empty")
    private String isbn;
    @Positive(message = "count can't be negative value")
    private int count;
    @NotBlank(message = "Image can't be empty")
    private String image;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "publishedTime can't be empty")
    private LocalDate publishedTime;
    @NotNull
    private Long categories_id;
    @NotBlank(message = "description can't be empty")
    @Size(max = 25, min = 5)
    private String description;

}
