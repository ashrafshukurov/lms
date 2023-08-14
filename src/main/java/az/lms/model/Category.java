/*
 *Created by Jaweed.Hajiyev
 *Date:10.08.23
 *TIME:13:15
 *Project name:LMS
 */

package az.lms.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @NotBlank
    @Column(name = "description")
    private String description;

//    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
//    private List<Book> book;
}
