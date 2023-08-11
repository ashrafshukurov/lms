package az.lms.model;

import lombok.*;

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
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String isbn;
    @Column(name ="book_image")
    private String image;
    @Column(name = "book_count")
    private int count;
    @Column(name = "book_name",nullable = false)
    private String name;
    @Column(name = "published_time")
    private LocalDate publishedTime;
    @Column(name = "author_name",nullable = false)
    private String authorName;


    @ManyToMany(mappedBy ="books")
    private Set<Authors> authors;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categories_id")
    private Set<Categories> categories;

    @ManyToMany(mappedBy = "borrowedBooks")
    private Set<Student> borrowers;

}
