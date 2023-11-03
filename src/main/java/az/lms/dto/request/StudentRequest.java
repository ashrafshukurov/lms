package az.lms.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * @author Mehman Osmanov on 12.08.23
 * @project LMS
 */
@Data
public class StudentRequest {
   @ApiModelProperty(notes = "Student FIN", example = "123asdf", required = true)
   @Size(min = 7, max = 7)
   @NotBlank(message = "FIN cannot be empty")
   private String FIN;
   @ApiModelProperty(notes = "Email", example = "example@gmail.com", required = true)
   @NotNull
   @Email(regexp = "^[a-zA-Z0-9]+@gmail\\.com$", message = "Invalid email address")
   private String email;
   @ApiModelProperty(notes = "Password", example = "123123123", required = true)
   @Size(min = 8)
   @NotBlank(message = "Password cannot be empty")
   private String password;
   @ApiModelProperty(notes = "Name", example = "James", required = true)
   @NotBlank(message = "Name cannot be empty")
   private String name;
   @ApiModelProperty(notes = "Surname", example = "Brown", required = true)
   @NotBlank(message = "Surname cannot be empty")
   private String surName;
   @ApiModelProperty(notes = "Group", example = "A125", required = true)
   @NotBlank(message = "Group number cannot be empty")
   private String studentGroup;
   @ApiModelProperty(notes = "BirthDate", example = "12.12.2002", required = true)
   @NotNull
   private LocalDate birthDate;
   @ApiModelProperty(notes = "Address", example = "123 Main St, City", required = true)
   private String address;

}
