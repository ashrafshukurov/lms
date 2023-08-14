/*
 *Created by Jaweed.Hajiyev
 *Date:13.08.23
 *TIME:23:13
 *Project name:LMS
 */

package az.lms.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
}
