package az.lms.dto.request;

import az.lms.enums.OrderType;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;
/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */
@Data
public class OrderRequest {
   @ApiModelProperty(notes = "Student ID", example = "1", required = true)
   @NotNull(message = "Student ID cannot be empty")
   private Long studentId;
   @ApiModelProperty(notes = "Book ID", example = "1", required = true)
   @NotNull(message = "Book ID cannot be empty")
   private Long bookId;
   @ApiModelProperty(notes = "Order type", example = "BORROWED/RETURNED", required = true)
   @NotNull(message = "Order type cannot be empty")
   private OrderType orderType;
}
