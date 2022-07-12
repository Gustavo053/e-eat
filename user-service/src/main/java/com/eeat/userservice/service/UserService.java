package com.eeat.userservice.service;

import com.eeat.userservice.model.Order;
import com.eeat.userservice.model.UserPlataform;
import com.eeat.userservice.repository.UserRepository;
import com.eeat.userservice.service.kafka.ProducerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProducerOrderService producerOrderService;

    public List<UserPlataform> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserPlataform> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserPlataform save(UserPlataform user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void createOrder(Order order) throws Exception {
        producerOrderService.send(order);
    }

    public Order findOrderById(Long id) {
        return restaurantService.findOrderById(id);
    }
}
