package com.uom.supermarketbackend.service;

import com.uom.supermarketbackend.dto.DeliveryDTO;
import com.uom.supermarketbackend.model.Delivery;
import com.uom.supermarketbackend.model.DeliveryPerson;
import com.uom.supermarketbackend.model.Order;
import com.uom.supermarketbackend.model.User;
import com.uom.supermarketbackend.repository.DeliveryPersonRepository;
import com.uom.supermarketbackend.repository.DeliveryRepository;
import com.uom.supermarketbackend.repository.OrderRepository;
import com.uom.supermarketbackend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

public class DeliveryService {
    private final UserRepository userRepository;
    private final DeliveryRepository deliveryRepository;

    private final OrderRepository orderRepository;
    private  final DeliveryPersonRepository deliveryPersonRepository;

    public DeliveryService(UserRepository userRepository, DeliveryRepository deliveryRepository, OrderRepository orderRepository, DeliveryPersonRepository deliveryPersonRepository) {
        this.userRepository = userRepository;
        this.deliveryRepository = deliveryRepository;
        this.orderRepository = orderRepository;
        this.deliveryPersonRepository = deliveryPersonRepository;
    }

    //Create Delivery(that create by Admin )
    public DeliveryDTO createDelivery(DeliveryDTO deliveryDTO) throws Exception{
        User user = userRepository.findById(deliveryDTO.getCustomerID())
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        Order order = orderRepository.findById(deliveryDTO.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        DeliveryPerson deliveryPerson=deliveryPersonRepository.findById(deliveryDTO.getDeliverPersonId())
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        Delivery delivery=new Delivery();
        delivery.setDeliveryId(deliveryDTO.getDeliveryId());
        delivery.setOrder(order);
        delivery.setTrackingId(deliveryDTO.getTrackingId());
        delivery.setShippingAddress(deliveryDTO.getShippingAddress());
        delivery.setDeliverDate(deliveryDTO.getDeliverDate());
        delivery.setDeliveryStatus(deliveryDTO.getDeliveryStatus());
        delivery.setDeliveryPerson(deliveryPerson);
        delivery.setUser(user);

        deliveryRepository.save(delivery);
        return deliverToDeliverDTO(delivery);
    }

    //Update Delivery progress(that update by deliveryPerson )
    public DeliveryDTO updateDeliveryStatus(Long deliveryId, String newStatus) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found"));

        // Update the delivery status in both the Delivery and Order entities
        delivery.setDeliveryStatus(newStatus);

        // Update the order's delivery status
        Order order = delivery.getOrder();
        if (order != null) {
            order.setDeliveryStatus(newStatus);
            orderRepository.save(order);
        }

        deliveryRepository.save(delivery);

        return deliverToDeliverDTO(delivery);
    }

    //past dat to Delivery entity to Dto
    public DeliveryDTO deliverToDeliverDTO(Delivery delivery){
        return DeliveryDTO.builder()
                .deliveryId(delivery.getDeliveryId())
                .customerID(delivery.getUser().getId())
                .trackingId(delivery.getTrackingId())
                .deliveryStatus(delivery.getDeliveryStatus())
                .deliverDate(delivery.getDeliverDate())
                .orderId(delivery.getOrder().getOrderId())
                .shippingAddress(delivery.getShippingAddress())
                .deliverPersonId(delivery.getDeliveryPerson().getId())
                .build();
    }
}
