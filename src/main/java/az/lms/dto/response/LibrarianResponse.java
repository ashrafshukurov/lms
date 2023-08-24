/*
 *Created by Jaweed.Hajiyev
 *Date:22.08.23
 *TIME:16:32
 *Project name:LMS
 */

package az.lms.dto.response;

import az.lms.enums.RoleType;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LibrarianResponse {
    private String email;
    private String name;
    private String surname;
    private RoleType roleType;
}
