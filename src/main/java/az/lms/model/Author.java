/*
 *Created by Jaweed.Hajiyev
 *Date:10.08.23
 *TIME:12:52
 *Project name:LMS
 */

package az.lms.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "first_name", nullable = false, unique = true)
    private String name;

    @Column(name = "last_name", unique = true)
    private String surname;

    @Column(name = "biography")
    private String biography;

    @NotBlank
    @Column(name = "birth_day")
    private LocalDateTime birthDay;

    @NotBlank
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "authors_book",
            joinColumns = @JoinColumn(name = "authors_id"),
            inverseJoinColumns = @JoinColumn(name = "books_id")
    )
    private Set<Book> books;
}
