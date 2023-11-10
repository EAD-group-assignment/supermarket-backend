package com.uom.supermarketbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

    private Long id;
    private String productName;
    private Integer prodQuantity;
    private Integer prodSubTotal;
    private List<OrderItemDTO> orderItems;

}