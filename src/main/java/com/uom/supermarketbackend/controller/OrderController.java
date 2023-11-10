package com.uom.supermarketbackend.controller;

import com.uom.supermarketbackend.dto.DeliveryDTO;
import com.uom.supermarketbackend.dto.OrderDTO;
import com.uom.supermarketbackend.service.DeliveryService;
import com.uom.supermarketbackend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    public OrderController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) throws Exception {

        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);

    }
    @PatchMapping("/update/{orderId}")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO) {
        OrderDTO updatedOrder = orderService.updateOrderStatus(orderId, orderDTO.getOrderStatus());
        return ResponseEntity.ok(updatedOrder);
    }

    @PostMapping("/{orderId}/create-delivery")
    public ResponseEntity<DeliveryDTO> createDeliveryForOrder(
            @PathVariable Long orderId,
            @RequestBody DeliveryDTO deliveryDTO) throws Exception {
        DeliveryDTO createdDelivery = deliveryService.createDelivery(deliveryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDelivery);
    }




//    @DeleteMapping("/delete/{orderId}")
//    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
//        orderService.deleteOrder(orderId);
//        return ResponseEntity.noContent().build();
//    }
}
