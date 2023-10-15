package com.uom.supermarketbackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
