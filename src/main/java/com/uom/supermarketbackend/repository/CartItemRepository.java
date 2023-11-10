package com.uom.supermarketbackend.repository;

import com.uom.supermarketbackend.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem ,Long> {
}
