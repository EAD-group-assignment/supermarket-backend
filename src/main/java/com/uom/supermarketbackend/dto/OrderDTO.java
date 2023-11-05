package com.uom.supermarketbackend.dto;

import com.uom.supermarketbackend.model.OrderItem;
import com.uom.supermarketbackend.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private int orderId;

    private String customerName;

    private List<OrderItem> orderItems;

    private OrderStatus status;

    private Date orderedDate;

    private BigDecimal amount;
}
