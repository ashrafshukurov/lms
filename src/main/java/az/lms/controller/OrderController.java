package az.lms.controller;


import az.lms.dto.request.OrderRequest;
import az.lms.dto.response.OrderResponse;
import az.lms.model.OrderType;
import az.lms.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

   private final OrderService orderService;


   @ApiOperation(value = "Get all orders", notes = "This method gets list of all available orders")
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "SUCCESSFUL"),
   })
   @GetMapping("/")
   public ResponseEntity<List<OrderResponse>> getOrders() {
      return ResponseEntity.ok(orderService.getOrders());
   }

   @ApiOperation(value = "Creating new order", notes = "Pass required order request to make successfully order")
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Successfully ordered"),
           @ApiResponse(code = 400, message = "Invalid insert")
   })
   @PostMapping("/borrow")
   public ResponseEntity<String> borrowOrder(@ApiParam(name = "request", value = "Order request object") @Valid @RequestBody OrderRequest request) {
      return ResponseEntity.ok(orderService.borrowOrder(request));
   }

   @ApiOperation(value = "Creating new return", notes = "Pass required order request to make successfully return")
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Successfully returned"),
           @ApiResponse(code = 400, message = "Invalid insert")
   })
   @PostMapping("/return")
   public ResponseEntity<String> returnOrder(@ApiParam(name = "request", value = "Order request object") @Valid @RequestBody OrderRequest request) {
      return ResponseEntity.ok(orderService.returnOrder(request));
   }

}
