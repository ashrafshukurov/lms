package az.lms.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * @author Mehman Osmanov on 12.08.23
 * @project LMS
 */
@Data
public class StudentRequest {
   @NotBlank(message = "FIN cannot be empty")
   private String FIN;
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
