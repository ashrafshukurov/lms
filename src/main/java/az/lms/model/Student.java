package az.lms.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ashraf
 * @project LMS
 */
@Entity
@Table(name = "students")
@Setter
@Getter
public class Student {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;


   @NotBlank
   @Column(length = 55)
   private String finCode;
   @NotBlank
   @Column(name = "firstName", length = 55)
   private String name;
   @NotBlank
   @Column(name = "last_name", length = 55)
   private String surName;
   @Min(17)
   private int age;
   @NotBlank
   private String group;
   @Column(length = 55)
   private String image;
   @Column(length = 100)
   private String address;
   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "book_id")
   private Book borrowedBooks;

}
