/*
 *Created by Jaweed.Hajiyev
 *Date:10.08.23
 *TIME:12:52
 *Project name:LMS
 */

package az.lms.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Authors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;
    String about;
    @ManyToMany
    Set<Books> books;

}
