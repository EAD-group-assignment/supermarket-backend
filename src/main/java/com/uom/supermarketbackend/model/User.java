package com.uom.supermarketbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@OneToOne(cascade = CascadeType.ALL)
@JsonIgnore
    private Cart cart;


    public User(Long id, Cart cart) {
        this.id = id;
        this.cart = cart;
    }

    public User() {

    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
