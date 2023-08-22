/*
 *Created by Jaweed.Hajiyev
 *Date:21.08.23
 *TIME:12:38
 *Project name:LMS
 */

package az.lms.dto.request;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginRequest {
    private String email;
    private String password;
}
