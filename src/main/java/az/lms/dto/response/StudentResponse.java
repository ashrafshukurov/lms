package az.lms.dto.response;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * @author Mehman Osmanov on 12.08.23
 * @project LMS
 */
@Data
public class StudentResponse {
   @NotBlank
   private String finCode;
   @NotBlank
   private String name;
   @NotBlank
   private String surName;
   @NotBlank
   private String studentGroup;
   private LocalDate birthDate;
   private String address;
}
