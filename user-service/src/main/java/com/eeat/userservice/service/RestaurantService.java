package com.eeat.userservice.service;

import com.eeat.userservice.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "restaurant-service")
public interface RestaurantService {
    @GetMapping(path = "/restaurant/order/{id}")
    Order findOrderById(@PathVariable Long id);
}
