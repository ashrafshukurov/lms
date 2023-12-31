package az.lms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
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

    @Column(unique = true, nullable = false)
    private String isbn;
    @Column(name = "book_image")
    private String image;
    @Column(name = "book_count")
    private int count;
    @Column(name = "book_name", nullable = false)
    private String name;
    @Column(name = "published_time")
    private LocalDate publishedTime;
    @Column(name = "details")
    private String details;
    private String description;

    @ManyToMany(mappedBy = "books")
    @JsonBackReference
    private Set<Author> authors;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonBackReference
    private Category categories;
    public Set<Author> getAuthors(){
        if(authors==null){
            authors=new HashSet<>();
        }
        return authors;
    }

}
