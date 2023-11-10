package com.uom.supermarketbackend.controller;

import com.uom.supermarketbackend.dto.DeliveryDTO;
import com.uom.supermarketbackend.dto.DeliveryUpdateDTO;
import com.uom.supermarketbackend.model.DeliveryPerson;
import com.uom.supermarketbackend.service.DeliveryPersonService;
import com.uom.supermarketbackend.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")

public class DeliveryController {
    private final DeliveryPersonService deliveryPersonService;

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryPersonService deliveryPersonService, DeliveryService deliveryService) {
        this.deliveryPersonService = deliveryPersonService;
        this.deliveryService = deliveryService;
    }




    //create delivery person
    @PostMapping("/create")
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
