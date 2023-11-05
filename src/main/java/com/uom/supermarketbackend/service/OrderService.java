package com.uom.supermarketbackend.service;

import com.uom.supermarketbackend.dto.OrderDTO;
import com.uom.supermarketbackend.model.Order;
import com.uom.supermarketbackend.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public List<Order> getAllOrders() {
        List<Order>orderList =  orderRepository.findAll();
        return modelMapper.map(orderList,new TypeToken<List<OrderDTO>>(){}.getType());
    }

    public OrderDTO getOrderDetails(int orderId) {
        Order order = orderRepository.getOrderDetails(orderId);
        return modelMapper.map(order,OrderDTO.class);
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        orderRepository.save(modelMapper.map(orderDTO, Order.class));
        return orderDTO;
    }
}

