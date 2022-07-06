package com.eeat.restaurantservice.controller;

import com.eeat.restaurantservice.model.EOrderStatus;
import com.eeat.restaurantservice.model.OrderUser;
import com.eeat.restaurantservice.repository.OrderRepository;
import com.eeat.restaurantservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(path = "/restaurant/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MessageService messageService;

    @GetMapping(path = "/{id}")
    public OrderUser findOrderById(@PathVariable Long id) {
        Optional<OrderUser> orderUserOptional = orderRepository.findById(id);

        if (!orderUserOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, messageService.get("order.not-found"));
        }

        return orderUserOptional.get();
    }

    @PostMapping(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderUser createOrder(@RequestBody OrderUser order) {
        order.setStatus(EOrderStatus.CREATED);
        return orderRepository.save(order);
    }

    @PutMapping(path = "/confirm-order/{id}")
    public OrderUser confirmOrder(@PathVariable Long id) {
        Optional<OrderUser> orderUserOptional = orderRepository.findById(id);

        if (!orderUserOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, messageService.get("order.not-found"));
        }

        OrderUser orderUser = orderUserOptional.get();
        orderUser.setStatus(EOrderStatus.CONFIRM_ORDER);
        return orderRepository.save(orderUser);
    }

    @PutMapping(path = "/send-order/{id}")
    public OrderUser sendOrder(@PathVariable Long id) {
        Optional<OrderUser> orderUserOptional = orderRepository.findById(id);

        if (!orderUserOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, messageService.get("order.not-found"));
        }

        OrderUser orderUser = orderUserOptional.get();
        orderUser.setStatus(EOrderStatus.SENDED_ORDER);
        return orderRepository.save(orderUser);
    }
}
