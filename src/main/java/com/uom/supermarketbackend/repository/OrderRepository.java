package com.uom.supermarketbackend.repository;

import com.uom.supermarketbackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM ORDER WHERE ID = ?1",nativeQuery = true)
    Order getOrderDetails(int orderId);
}
