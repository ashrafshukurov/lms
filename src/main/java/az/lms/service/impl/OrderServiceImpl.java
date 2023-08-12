package az.lms.service.impl;

import az.lms.dto.request.OrderRequest;
import az.lms.dto.response.OrderResponse;
import az.lms.model.OrderType;
import az.lms.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
   @Override
   public List<OrderResponse> getOrdersList() {
      return null;
   }

   @Override
   public OrderType createOrder(OrderRequest request) {
      return null;
   }

   @Override
   public OrderType returnOrder(OrderRequest request) {
      return null;
   }
}
