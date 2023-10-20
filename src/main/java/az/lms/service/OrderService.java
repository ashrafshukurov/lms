package az.lms.service;

import az.lms.dto.request.OrderRequest;
import az.lms.dto.response.OrderResponse;

import java.util.List;

/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */
public interface OrderService {
   List<OrderResponse> getOrders();
   void borrowOrder(OrderRequest request);
   void returnOrder(OrderRequest request);
}