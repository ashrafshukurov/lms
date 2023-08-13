package az.lms.controller;


import az.lms.dto.request.OrderRequest;
import az.lms.dto.response.OrderResponse;
import az.lms.model.OrderType;
import az.lms.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {

   private final OrderService orderService;

   @GetMapping("/")
   public ResponseEntity<List<OrderResponse>> getOrders() {
      return ResponseEntity.ok(orderService.getOrders());
   }

   @PostMapping("/make")
   public OrderType makeOrder(@RequestBody OrderRequest request) {
      return orderService.createOrder(request);
   }

   @PostMapping("/return")
   public OrderType returnOrder(@RequestBody OrderRequest request) {
      return orderService.returnOrder(request);
   }

}
