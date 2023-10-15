package com.uom.supermarketbackend.controller;

import com.uom.supermarketbackend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    /*
    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO) {
        Long orderId = orderService.createOrder(orderDTO);
        return ResponseEntity.ok("Order created with ID: " + orderId);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderDetails(@PathVariable Long orderId) {
        OrderDTO orderDTO = orderService.getOrderDetails(orderId);
        return ResponseEntity.ok(orderDTO);
    }

    @PatchMapping("/update-status/{orderId}")
    public ResponseEntity<String> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestBody OrderStatusUpdateDTO statusUpdateDTO) {
        orderService.updateOrderStatus(orderId, statusUpdateDTO.getStatus());
        return ResponseEntity.ok("Order status updated successfully");
    }
    */

}
