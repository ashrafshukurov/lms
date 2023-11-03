/*
 *Created by Jaweed.Hajiyev
 *Date:21.08.23
 *TIME:12:38
 *Project name:LMS
 */

package az.lms.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginRequest {
    @ApiModelProperty(notes = "Email", example = "example@gmail.com", required = true)
    private String email;
    @ApiModelProperty(notes = "Password", example = "Example123", required = true)
    private String password;
}
