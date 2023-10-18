package az.lms.dto.request;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * @author Mehman Osmanov on 12.08.23
 * @project LMS
 */
@Data
public class StudentRequest {
   @Size(min = 7, max = 7)
   @NotBlank(message = "FIN cannot be empty")
   private String FIN;

   @Email(regexp = "^[a-zA-Z0-9]+@gmail\\.com$", message = "Invalid email address")
   private String email;

   @Size(min = 8)
   @NotBlank(message = "Password cannot be empty")
   private String password;
   @NotBlank(message = "Name cannot be empty")
   private String name;
   @NotBlank(message = "Surname cannot be empty")
   private String surName;
   @NotBlank(message = "Group number cannot be empty")
   private String studentGroup;
   private LocalDate birthDate;
   private String address;

}
