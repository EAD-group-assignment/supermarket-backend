package com.uom.supermarketbackend.service;

import com.uom.supermarketbackend.dto.OrderDTO;
import com.uom.supermarketbackend.dto.OrderItemDTO;
import com.uom.supermarketbackend.model.Order;
import com.uom.supermarketbackend.model.OrderItem;
import com.uom.supermarketbackend.model.Product;
import com.uom.supermarketbackend.model.User;
import com.uom.supermarketbackend.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private final DeliveryRepository deliveryRepository;

    private final DeliveryPersonRepository deliveryPersonRepository;


    public OrderDTO createOrder(OrderDTO orderDTO) throws Exception {

        User user = userRepository.findById(orderDTO.getUserID())
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        List<OrderItemDTO> orderProductDTOList =orderDTO.getOrderedProducts();

        //checking the inventory is sufficient to process order
        for(int i=0; i<orderProductDTOList.size();i++){
            OrderItemDTO itemProduct = orderProductDTOList.get(i);

            Product product = productRepository.findById(itemProduct.getId()).orElse(null);
            if(product != null){
                if(product.getQuantity() <= itemProduct.getProdQuantity()){
                    throw new Exception(" is currently out of stock");
                }
                else{
                    Integer newQuantity = 0;
                    newQuantity = product.getQuantity() - itemProduct.getProdQuantity();
                    product.setQuantity(newQuantity);
                }

            }
            else{
                throw new IllegalArgumentException("Product not Found");
            }
        }

        Order order= new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setOrderDate(new Date());
        order.setTotalQuantity(orderDTO.getTotalQuantity());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setOrderStatus("processing");
        order.setDeliveryStatus("pending");
        order.setUser(user);

        List<OrderItem> orderProducts = new ArrayList<>();

        //setting products in OrderProduct entity
        for(int i = 0; i < orderProductDTOList.size();i++){
            OrderItemDTO prodOrder =orderProductDTOList.get(i);

            Product product = productRepository.findById(prodOrder.getId())
                    .orElseThrow(() ->new IllegalArgumentException("Product Not Found"));

            OrderItem orderProduct = new OrderItem();
            orderProduct.setProdQuantity(prodOrder.getProdQuantity());
            orderProduct.setProdTotalPrice(prodOrder.getProdSubTotal());
            orderProduct.setProduct(product);
            orderProduct.setOrder(order);

            //add product to arraylist
            orderProducts.add(orderProduct);

        }

        order.setOrderedProducts(orderProducts);
        orderRepository.save(order);

        return orderToOrderDTO(order);
    }

    //cancel order by customer
    public OrderDTO cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

//        order.setOrderStatus("canceled");
//        orderRepository.save(order);
        orderRepository.deleteById(orderId);
        return orderToOrderDTO(order);
    }


    //Update Order progress(that update by admin )
    public OrderDTO updateOrderStatus(Long orderId, String newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        // Update order status
        order.setOrderStatus(newStatus);
        orderRepository.save(order);

        return orderToOrderDTO(order);
    }


    //get order details by customer
    public OrderDTO getOrderDetails(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElse(null);

        if (order != null) {
            return orderToOrderDTO(order);
        } else {
            throw new EntityNotFoundException("Order not found");
        }
    }
    private OrderDTO orderToOrderDTO(Order order) {
        List<OrderItem> orderProducts = order.getOrderedProducts();
        List<OrderItemDTO> orderProductDTOs = orderProducts.stream()
                .map(this::orderProductToOrderProductDTO)
                .collect(Collectors.toList());

        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .totalQuantity(order.getTotalQuantity())
                .totalPrice(order.getTotalPrice())
                .orderDate(new Date())
                .orderStatus(order.getOrderStatus())
                .userID(order.getUser().getId())
                .deliveryStatus(order.getDeliveryStatus())
                /*.paymentID(order.getPayment().getPaymentId())
                .deliveryID(order.getDelivery().getDeliveryId())*/
                .orderedProducts(orderProductDTOs)
                .build();
    }

    private OrderItemDTO orderProductToOrderProductDTO(OrderItem orderItem) {
        // Map OrderProduct entity to OrderProductDTO
        return OrderItemDTO.builder()
                .id(orderItem.getId())
                .productName(orderItem.getProduct().getProductName()) // Change this according to your actual structure
                .prodQuantity(orderItem.getProdQuantity())
                .prodSubTotal(orderItem.getProdTotalPrice())
                .build();
    }

}