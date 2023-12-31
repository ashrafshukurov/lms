package az.lms.model;

import az.lms.enums.RoleType;
import lombok.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author ashraf
 * @project LMS
 */
@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 55, unique = true)
    private String FIN;

    @Column(name = "e_mail")
    private String email;

    @Column(nullable = false)
    private String password;
    @Column(name = "first_name", length = 55)
    private String name;
    @Column(name = "last_name", length = 55)
    private String surName;
    private LocalDate birthDate;
    @Column(name = "student_group", length = 10)
    private String studentGroup;
    @Column(length = 100)
    private String address;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role_type")
    private RoleType roleType;

}
