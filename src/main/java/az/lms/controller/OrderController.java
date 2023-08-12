package az.lms.controller;


import az.lms.dto.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

   @GetMapping("/")
   public ResponseEntity<List<OrderResponse>> getOrders(){
      return null;
   }



}
