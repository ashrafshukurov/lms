package az.lms.mapper;

import az.lms.dto.request.OrderRequest;
import az.lms.dto.response.OrderResponse;
import az.lms.model.Order;
import org.mapstruct.Mapper;

/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */
@Mapper(componentModel = "spring")
public interface OrderMapper {
   Order dtoToEntity(OrderRequest request);
   OrderResponse entityToDto(Order order);
}
