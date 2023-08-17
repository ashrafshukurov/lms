/*
 *Created by Jaweed.Hajiyev
 *Date:13.08.23
 *TIME:23:13
 *Project name:LMS
 */

package az.lms.dto.request;

import az.lms.model.Book;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    @NotBlank(message = "Invalid Name: Empty name")
    @Size(max = 25, min = 2,message = "Invalid Name: Must be of 2 - 255 characters")
    @ApiModelProperty(name = "Category name",required = true,notes = "Category must be unique")
    private String name;

    @NotBlank(message = "Invalid Name: Empty description")
    @Size(max = 255, min = 5, message = "Invalid Name: Must be of 5 - 255 characters")
    @ApiModelProperty(name = "Category description")
    private String description;
}