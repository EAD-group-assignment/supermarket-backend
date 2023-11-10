package com.uom.supermarketbackend.repository;

import com.uom.supermarketbackend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByUser_Id(Long userId);
}
