package com.uom.supermarketbackend.controller;

import com.uom.supermarketbackend.dto.DeliveryDTO;
import com.uom.supermarketbackend.dto.DeliveryUpdateDTO;
import com.uom.supermarketbackend.dto.OrderDTO;
import com.uom.supermarketbackend.model.DeliveryPerson;
import com.uom.supermarketbackend.model.User;
import com.uom.supermarketbackend.service.DeliveryPersonService;
import com.uom.supermarketbackend.service.DeliveryService;
import com.uom.supermarketbackend.service.OrderService;
import com.uom.supermarketbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private final DeliveryPersonService deliveryPersonService;

    @Autowired
    private final DeliveryService deliveryService;

    public AuthController(DeliveryPersonService deliveryPersonService, DeliveryService deliveryService) {
        this.deliveryPersonService = deliveryPersonService;
        this.deliveryService = deliveryService;
    }



    @PostMapping("/create")
    public ResponseEntity<User> createCustomer(@RequestBody User user) {
        User createdUser = userService.createCustomer(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    //track order details by customer
    @GetMapping("/{orderId}/order-details")
    public ResponseEntity<OrderDTO> getOrderDetails(@PathVariable Long orderId) {
        OrderDTO order = orderService.getOrderDetails(orderId);

        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Cancel order by customer
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable Long orderId) {
        OrderDTO canceledOrder = orderService.cancelOrder(orderId);
        return ResponseEntity.ok(canceledOrder);
    }






    //create delivery person
    @PostMapping("/createDeliveryPerson")
    public ResponseEntity<DeliveryPerson> saveDeliveryPerson(@RequestBody DeliveryPerson deliveryPerson) {
        DeliveryPerson createdDeliveryPerson = deliveryPersonService.saveDeliveryPerson(deliveryPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDeliveryPerson);
    }

    //create delivery status by delivery person
    @PutMapping("/{deliveryId}/update-status")
    public ResponseEntity<DeliveryDTO> updateDeliveryStatus(
            @PathVariable Long deliveryId,
            @RequestBody DeliveryUpdateDTO deliveryUpdateDTO){
        String newStatus = deliveryUpdateDTO.getNewStatus();
        DeliveryDTO updatedDelivery = deliveryService.updateDeliveryStatus(deliveryId, newStatus);
        return ResponseEntity.ok(updatedDelivery);

    }

}
