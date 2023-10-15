package com.uom.supermarketbackend.controller;

import com.uom.supermarketbackend.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    /*
    @PostMapping("/accept/{deliveryId}/{deliveryPersonId}")
    public ResponseEntity<String> acceptDelivery(
        @PathVariable Long deliveryId,
        @PathVariable Long deliveryPersonId
    ) {
        deliveryService.acceptDelivery(deliveryId, deliveryPersonId);
        return ResponseEntity.ok("Delivery accepted successfully");
    }

    @PatchMapping("/update-status/{deliveryId}")
    public ResponseEntity<String> updateDeliveryStatus(
        @PathVariable Long deliveryId,
        @RequestBody DeliveryStatusUpdateDTO statusUpdateDTO
    ) {
        deliveryService.updateDeliveryStatus(deliveryId, statusUpdateDTO.getStatus());
        return ResponseEntity.ok("Delivery status updated successfully");
    }
    */

}
