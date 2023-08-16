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
import javax.validation.constraints.Size;
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

    @NotBlank(message = "Invalid Name: Empty name")
    @Size(max = 25, min = 2,message = "Invalid Name: Must be of 2 - 255 characters")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @NotBlank(message = "Invalid Name: Empty description")
    @Size(max = 255, min = 5,message = "Invalid Name: Must be of 5 - 255 characters")
    @Column(name = "description",nullable = false)
    private String description;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "categories")
    private List<Book> books;
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

