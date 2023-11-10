package com.uom.supermarketbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDTO {
    private Long deliveryId;
    private Long orderId;
    private String trackingId;
    private String shippingAddress;
    private String deliveryStatus;
    private Long customerID;
    private String deliverDate;
    private Long deliverPersonId;
    // Other fields as needed
}