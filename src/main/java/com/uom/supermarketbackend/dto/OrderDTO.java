package com.uom.supermarketbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private Integer totalQuantity;
    private BigDecimal totalPrice;
    private Date orderDate;
    private String orderStatus;
    private String deliveryStatus;
    private Long userID;
    private Integer deliveryID;
    private List<OrderItemDTO> orderedProducts;

}