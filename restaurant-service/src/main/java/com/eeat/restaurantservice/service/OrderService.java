package com.eeat.restaurantservice.service;

import com.eeat.restaurantservice.model.OrderUser;
import com.eeat.restaurantservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<OrderUser> findAll() {
        return orderRepository.findAll();
    }

    public Optional<OrderUser> findById(Long id) {
        return orderRepository.findById(id);
    }

    public OrderUser save(OrderUser order) {
        return orderRepository.save(order);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
