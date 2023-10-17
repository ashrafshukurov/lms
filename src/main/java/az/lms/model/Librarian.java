/*
 *Created by Jaweed.Hajiyev
 *Date:22.08.23
 *TIME:16:09
 *Project name:LMS
 */

package az.lms.model;

import az.lms.enums.RoleType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "librarian")
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "e_mail")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name",nullable = false)
    private String name;
    @Column(name = "last_name")
    private String surname;
    @Enumerated(EnumType.STRING)
    @Column(name = "role_type",nullable = false)
    private RoleType roleType;
}
