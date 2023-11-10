package com.uom.supermarketbackend.repository;

import com.uom.supermarketbackend.model.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson,Long> {

}
