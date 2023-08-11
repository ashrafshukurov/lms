package az.lms.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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
    @Min(17)
    private int age;
    @NotBlank
    private String student_group;

    @Column(length = 100)
    private String address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "books_id")
    private Book borrowedBooks;

}
