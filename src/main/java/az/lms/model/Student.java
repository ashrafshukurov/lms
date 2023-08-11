package az.lms.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ashraf
 * @project LMS
 */
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "student_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "book_id",referencedColumnName = "id")})
    private Set<Book> borrowedBooks;

}
