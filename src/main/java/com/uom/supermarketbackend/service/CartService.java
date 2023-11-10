package com.uom.supermarketbackend.service;

import com.uom.supermarketbackend.model.Cart;
import com.uom.supermarketbackend.model.CartItem;
import com.uom.supermarketbackend.model.Product;
import com.uom.supermarketbackend.model.User; // Assuming you have a Customer model

import com.uom.supermarketbackend.repository.CartItemRepository;
import com.uom.supermarketbackend.repository.CartRepository;
import com.uom.supermarketbackend.repository.ProductRepository;
import com.uom.supermarketbackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CartItem> addToCart(Long userId, Long productId, int quantity) {
        // Retrieve the user
        User user = userRepository.findById(userId).orElseThrow();

        if (user != null) {
            // Check if the user has a cart
            Cart cart = user.getCart();

            if (cart == null) {
                // If the user doesn't have a cart, create one
//                cart = new Cart();
                cart = Cart.builder()
                        .user(user)
                        .build();
//
//                 Initialize cart items if null
                if (cart.getCartItems() == null) {
                    cart.setCartItems(new ArrayList<>());
                }

                user.setCart(cart);
                cartRepository.save(cart);
                userRepository.save(user);

//                cart.setUser(user);
//                user.setCart(cart);
//                cartRepository.save(cart);
//                userRepository.save(user);
//                System.out.println(user.getCart());
//                System.out.println(cart.getUser());
            }

            // Initialize cart items if null
//            if (cart.getCartItems() == null) {
//                cart.setCartItems(new ArrayList<>());
//            }

            // Retrieve the product
            Product product = productRepository.findById(productId).orElse(null);

            if (product != null) {
                // Check if the product is already in the cart
                CartItem existingItem = cart.getCartItems()
                        .stream()
                        .filter(item -> item.getProduct().getId().equals(productId))
                        .findFirst()
                        .orElse(null);

                if (existingItem != null) {
                    // If the product is already in the cart, update the quantity
                    existingItem.setQuantity(existingItem.getQuantity() + quantity);
                } else {
                    // If the product is not in the cart, add a new cart item
                    CartItem newItem = new CartItem();
                    newItem.setProduct(product);
                    newItem.setQuantity(quantity);
                    newItem.setCart(cart);
                    cart.getCartItems().add(newItem);
                }

                // Save the updated cart and user
                cartRepository.save(cart);
                userRepository.save(user);
            } else {
                System.out.println("Unknown product");
            }
        }

        return user.getCart().getCartItems(); // Return the user's updated cart
    }


    public List<CartItem> getUserCart(Long userId) {
        Cart cart = cartRepository.findByUser_Id(userId);
        if (cart == null) {
            // Handle the case where the user doesn't have a cart
            throw new NoSuchElementException("User with ID " + userId + " doesn't have a cart.");

        }
        return cart.getCartItems();

    }

    public Cart removeItemFromCart(Long userId, Long productId) {
        // Retrieve the user
        User user = userRepository.findById(userId).orElseThrow();

        if (user != null) {
            // Check if the user has a cart
            Cart cart = user.getCart();

            if (cart != null) {
                // Find the cart item to remove
                Optional<CartItem> itemToRemove = cart.getCartItems()
                        .stream()
                        .filter(item -> item.getProduct().getId().equals(productId))
                        .findFirst();

                if (itemToRemove.isPresent()) {
                    // Remove the item from the cart
                    System.out.println(itemToRemove.get());
                    cart.getCartItems().remove(itemToRemove.get());
                    // Update the cart and user
                    cartRepository.save(cart);
                    userRepository.save(user);
                }
            }
        }

        return user.getCart();
    }

    public List<CartItem> updateCartItemQuantity(Long userId, Long productId, int newQuantity) {
        // Retrieve the user
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            // Check if the user has a cart
            Cart cart = user.getCart();

            if (cart != null) {
                // Find the cart item to update
                Optional<CartItem> itemToUpdate = cart.getCartItems()
                        .stream()
                        .filter(item -> item.getProduct().getId().equals(productId))
                        .findFirst();

                if (itemToUpdate.isPresent()) {
                    // Update the quantity of the item
                    itemToUpdate.get().setQuantity(newQuantity);
                    // Update the cart and user
                    cartRepository.save(cart);
                    userRepository.save(user);
                }
                else {
                    System.out.println("product doesn't exist");
                    return null;
                }
            }
        }

        return user.getCart().getCartItems();
    }



}
