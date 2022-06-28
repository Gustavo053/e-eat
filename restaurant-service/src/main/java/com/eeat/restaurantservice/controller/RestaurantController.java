package com.eeat.restaurantservice.controller;

import com.eeat.restaurantservice.model.Restaurant;
import com.eeat.restaurantservice.service.MessageService;
import com.eeat.restaurantservice.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<Restaurant> findAll() {
        return restaurantService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Restaurant findById(@PathVariable Long id) {
        Optional<Restaurant> restaurantOptional = restaurantService.findById(id);

        if (!restaurantOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, messageService.get("restaurant.not-found"));
        }

        return restaurantOptional.get();
    }

    @GetMapping(path = "/{cnpj}")
    public Restaurant findByCnpj(@PathVariable Integer cnpj) {
        Optional<Restaurant> restaurantOptional = restaurantService.findByCnpj(cnpj);

        if (!restaurantOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, messageService.get("restaurant.not-found"));
        }

        return restaurantOptional.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant save(@RequestBody Restaurant restaurant) {
        return restaurantService.save(restaurant);
    }

    @PutMapping(path = "/{id}")
    public Restaurant update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        Optional<Restaurant> restaurantOptional = restaurantService.findById(id);

        if (!restaurantOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, messageService.get("restaurant.not-found"));
        }

        restaurant.setId(id);
        return restaurantService.save(restaurant);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        Optional<Restaurant> restaurantOptional = restaurantService.findById(id);

        if (!restaurantOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, messageService.get("restaurant.not-found"));
        }

        restaurantService.deleteById(id);
    }
}
