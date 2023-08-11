package az.lms.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ashraf
 * @project LMS
 */
@Setter
@Getter
public class BookRequest {
    private Long id;
    private String name;
    private String isbn;
    private int count;
    private String image;
}
