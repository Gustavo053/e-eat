package com.eeat.userservice.service;

import com.eeat.userservice.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "restaurant-service")
public interface RestaurantService {
    @PostMapping(path = "/restaurant/order/create")
    Order createOrder(@RequestBody Order order);

    @GetMapping(path = "/restaurant/order/{id}")
    Order findOrderById(@PathVariable Long id);
}
