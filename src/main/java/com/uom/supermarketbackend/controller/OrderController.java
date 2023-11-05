package com.uom.supermarketbackend.controller;

import com.uom.supermarketbackend.dto.OrderDTO;
import com.uom.supermarketbackend.model.Order;
import com.uom.supermarketbackend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO orderId = orderService.createOrder(orderDTO);
        return ResponseEntity.ok("Order created with ID: " + orderId);
    }
    @GetMapping(value = "/getAllOrders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
    @GetMapping("/{orderId}")
    public OrderDTO getOrderDetails(@PathVariable int orderId) {
        OrderDTO orderDTO = orderService.getOrderDetails(orderId);
        return orderDTO;
    }

//    @PatchMapping("/update-status/{orderId}")
//    public ResponseEntity<String> updateOrderStatus(@PathVariable Long orderId, @RequestBody OrderStatusUpdateDTO statusUpdateDTO) {
//        orderService.updateOrderStatus(orderId, statusUpdateDTO.getStatus());
//        return ResponseEntity.ok("Order status updated successfully");
//    }


}
