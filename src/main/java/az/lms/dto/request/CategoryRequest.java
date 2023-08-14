/*
 *Created by Jaweed.Hajiyev
 *Date:13.08.23
 *TIME:23:13
 *Project name:LMS
 */

package az.lms.dto.request;

import az.lms.model.Book;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
    private String name;
    private String description;
}