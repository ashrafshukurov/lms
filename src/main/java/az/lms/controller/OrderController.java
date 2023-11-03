package az.lms.controller;


import az.lms.dto.request.OrderRequest;
import az.lms.dto.response.OrderResponse;
import az.lms.service.OrderService;
import io.swagger.annotations.*;
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


   @ApiOperation(value = "Get all orders", notes = "This method gets list of all available orders",authorizations = { @Authorization(value="jwtToken") })
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "SUCCESSFUL"),
   })
   @GetMapping("/")
   public ResponseEntity<List<OrderResponse>> getOrders() {
      return ResponseEntity.ok(orderService.getOrders());
   }

   @ApiOperation(value = "Creating new order", notes = "Pass required order request to make successfully order",authorizations = { @Authorization(value="jwtToken") })
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Successfully ordered"),
           @ApiResponse(code = 400, message = "Invalid insert")
   })
   @PostMapping("/borrow")
   public ResponseEntity<String> borrowOrder(@ApiParam(name = "request", value = "Order request object") @Valid @RequestBody OrderRequest request) {
      orderService.borrowOrder(request);
      return ResponseEntity.ok("Successfully ordered");
   }

   @ApiOperation(value = "Creating new return", notes = "Pass required order request to make successfully return",authorizations = { @Authorization(value="jwtToken") })
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Successfully returned"),
           @ApiResponse(code = 400, message = "Invalid insert")
   })
   @PostMapping("/return")
   public ResponseEntity<String> returnOrder(@ApiParam(name = "request", value = "Order request object") @Valid @RequestBody OrderRequest request) {
      orderService.returnOrder(request);
      return ResponseEntity.ok("Successfully returned");
   }

}
