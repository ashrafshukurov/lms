/*
 *Created by Jaweed.Hajiyev
 *Date:10.08.23
 *TIME:12:52
 *Project name:LMS
 */

package az.lms.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
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

    @Column(name = "first_name", length = 25, nullable = false)
    private String name;

    @Column(name = "last_name", length = 25)
    private String surname;

    @Column(name = "biography")
    private String biography;

    @Column(name = "birth_day")
    private LocalDate birthDay;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "authors_book",
            joinColumns = @JoinColumn(name = "authors_id"),
            inverseJoinColumns = @JoinColumn(name = "books_id")
    )
    private Set<Book> books;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", biography='" + biography + '\'' +
                ", birthDay=" + birthDay +
                ", books=" + books +
                '}';
    }
}
