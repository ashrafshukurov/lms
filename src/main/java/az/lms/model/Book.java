package az.lms.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ashraf
 * @project LMS
 */
@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(unique = true)
    private String isbn;
    private String image;
    private int booksCount;
    private String bookName;
    private LocalDate publishedTime;
    private String authorName;

    @ManyToMany(mappedBy ="books")
    private Set<Authors> authors;

    @ManyToMany(mappedBy = "books")
    private Set<Category> categories;

    @ManyToMany(mappedBy = "borrowedBooks")
    private Set<Student> borrowers;

}
