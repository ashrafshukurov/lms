/*
 *Created by Jaweed.Hajiyev
 *Date:22.08.23
 *TIME:16:31
 *Project name:LMS
 */

package az.lms.dto.request;

import az.lms.enums.RoleType;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LibrarianRequest {
    private String email;
    private String password;
    private String name;
    private String surname;
    private RoleType roleType;
}
