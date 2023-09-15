/*
 *Created by Jaweed.Hajiyev
 *Date:22.08.23
 *TIME:16:31
 *Project name:LMS
 */

package az.lms.dto.request;

import az.lms.enums.RoleType;
import az.lms.validator.EnumNamePattern;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LibrarianRequest {
    @NotBlank(message = "Email cannot be empty")
    @Email(regexp = "^[a-zA-Z0-9]+@gmail\\.com$", message = "Invalid email address")
    @Size(max = 30, min = 10, message = "Invalid Email: Must be of 10 - 30 characters")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(max = 25, min = 6, message = "Invalid password: Password length must be min 6 characters")
    private String password;

    @NotBlank
    @Size(max = 25, min = 2, message = "Invalid Name: Must be of 2 - 25 characters")
    private String name;

    @Size(max = 25, message = "Invalid Name: Must be of 2 - 25 characters")
    private String surname;

    @EnumNamePattern
    private RoleType roleType;
}
