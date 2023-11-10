package com.uom.supermarketbackend.service;

import com.uom.supermarketbackend.model.DeliveryPerson;
import com.uom.supermarketbackend.repository.DeliveryPersonRepository;
import org.springframework.stereotype.Service;

@Service

public class DeliveryPersonService {
    private final DeliveryPersonRepository deliveryPersonRepository;


    public DeliveryPersonService(DeliveryPersonRepository deliveryPersonRepository) {
        this.deliveryPersonRepository = deliveryPersonRepository;

    }


    public DeliveryPerson saveDeliveryPerson(DeliveryPerson deliveryPerson) {
        return deliveryPersonRepository.save(deliveryPerson);
    }

}