/*
 *Created by Jaweed.Hajiyev
 *Date:10.08.23
 *TIME:13:15
 *Project name:LMS
 */

package az.lms.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
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
    @ApiModelProperty(name = "Category name",value = "Cavid")
    @Column(name = "name", unique = true, nullable = false)
    private String name;


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

