package az.lms.service;

import az.lms.dto.request.OrderRequest;
import az.lms.dto.response.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */
@Service
public interface OrderService {

   List<OrderResponse> getOrders();

   String borrowOrder(OrderRequest request);

   String returnOrder(OrderRequest request);

}
