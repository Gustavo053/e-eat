package com.eeat.restaurantservice.controller;

import com.eeat.restaurantservice.model.OrderUser;
import com.eeat.restaurantservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/restaurant/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderUser createOrder(@RequestBody OrderUser order) {
        return orderRepository.save(order);
    }
}
