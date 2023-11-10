package com.uom.supermarketbackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryUpdateDTO {
    private Long deliveryId;      // ID of the delivery to be updated
    private String newStatus;     // The new delivery status to set
}
