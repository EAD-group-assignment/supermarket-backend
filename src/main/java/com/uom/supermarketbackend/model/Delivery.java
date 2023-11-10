package com.uom.supermarketbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @GeneratedValue(strategy = GenerationType.UUID)
    private String trackingId;

    @ManyToOne
    @JoinColumn(name = "fk_C", referencedColumnName = "id")
    private User user;


    private String shippingAddress;
    private String deliveryStatus;

    private String deliverDate;


    @ManyToOne
    @JoinColumn(name = "delivery_person_id")
    private  DeliveryPerson deliveryPerson;



}
