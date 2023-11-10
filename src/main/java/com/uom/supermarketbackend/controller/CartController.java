package com.uom.supermarketbackend.controller;

import com.uom.supermarketbackend.dto.AddToCartRequest; // Make sure to import the DTO
import com.uom.supermarketbackend.dto.CartDTO; // Import the DTO for response
import com.uom.supermarketbackend.model.Cart; // Import the Cart model
import com.uom.supermarketbackend.model.CartItem;
import com.uom.supermarketbackend.model.Product; // Import the Product model
import com.uom.supermarketbackend.service.CartService;
import com.uom.supermarketbackend.service.ProductService;
import com.uom.supermarketbackend.service.UserService; // Assuming this is the service for managing customers

import com.uom.supermarketbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final UserService customerService;
    private final ProductService productService;

    @Autowired
    public CartController(CartService cartService, UserService customerService, ProductService productService) {
        this.cartService = cartService;
        this.customerService = customerService;
        this.productService = productService;
    }


    @PostMapping("/add-to-cart")
    public List<CartItem> addToCart(@RequestBody AddToCartRequest request) {
        return cartService.addToCart(request.getUserId(), request.getProductId(), request.getQuantity());
//        return ResponseEntity.ok(mapToCartDTO(updatedCart));
    }

    @GetMapping("/{userId}/cart")
    public List<CartItem> getCart(@PathVariable Long userId) {
        return cartService.getUserCart(userId);
//        return ResponseEntity.ok(mapToCartDTO(userCart));
    }

    @DeleteMapping("/{userId}/cart/removeitem/{productId}")
    public ResponseEntity<List<CartItem> > removeItemFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        List<CartItem>  updatedCart = cartService.removeItemFromCart(userId, productId);
        return ResponseEntity.ok(updatedCart);
    }

    @PutMapping("/{userId}/cart/updatequantity/{productId}")
    public ResponseEntity<List<CartItem>> updateItemQuantityInCart(@PathVariable Long userId, @PathVariable Long productId, @RequestParam int newQuantity) {
        List<CartItem> updatedCart = cartService.updateCartItemQuantity(userId, productId, newQuantity);
        return ResponseEntity.ok(updatedCart);
    }





}
