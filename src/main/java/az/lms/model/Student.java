package az.lms.model;

import lombok.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ashraf
 * @project LMS
 */
@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
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
    @Column(name = "first_name", length = 55)
    private String name;
    @NotBlank
    @Column(name = "last_name", length = 55)
    private String surName;
    private LocalDate birthDate;
    @NotBlank
    @Column(name = "student_group", length = 10)
    private String studentGroup;
    @Column(length = 100)
    private String address;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "books_id")
    private Book borrowedBooks;
   
}
