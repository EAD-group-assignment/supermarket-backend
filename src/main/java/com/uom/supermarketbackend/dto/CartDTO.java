package com.uom.supermarketbackend.dto;

import java.util.List;

public class CartDTO {
    private Long userId; // You may need to include user-related information
    private List<CartItemDTO> cartItems; // List of items in the cart

    public CartDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }
}
