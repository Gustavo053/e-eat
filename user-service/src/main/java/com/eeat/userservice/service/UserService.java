package com.eeat.userservice.service;

import com.eeat.userservice.model.Order;
import com.eeat.userservice.model.UserPlataform;
import com.eeat.userservice.repository.UserRepository;
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

    public List<UserPlataform> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserPlataform> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserPlataform save(UserPlataform user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void createOrder(Order order) {
        restaurantService.createOrder(order);
    }
}
