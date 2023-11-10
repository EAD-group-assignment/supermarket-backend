package com.uom.supermarketbackend.service;

import com.uom.supermarketbackend.model.User;
import com.uom.supermarketbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createCustomer(User user) {
        return userRepository.save(user);
    }


}