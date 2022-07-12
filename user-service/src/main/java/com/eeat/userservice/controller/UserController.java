package com.eeat.userservice.controller;

import com.eeat.userservice.DTO.UserCredentialsDTO;
import com.eeat.userservice.model.Order;
import com.eeat.userservice.model.UserPlataform;
import com.eeat.userservice.service.MessageService;
import com.eeat.userservice.service.UserCredentialsService;
import com.eeat.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserCredentialsService userCredentialsService;
    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<UserPlataform> findAll() {
        return userService.findAll();
    }

    @GetMapping(path = "/{id}")
    public UserPlataform findById(@PathVariable Long id) {
        Optional<UserPlataform> userOptional = userService.findById(id);

        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, messageService.get("user.not-found"));
        }

        return userOptional.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserPlataform save(@RequestBody UserPlataform user) {
        UserCredentialsDTO userCredentialsDTO = UserCredentialsDTO.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .enable(true)
                .build();

        userCredentialsService.createUserCredentials(userCredentialsDTO);
        user.setPassword(null);
        return userService.save(user);
    }

    @PutMapping(path = "/{id}")
    public UserPlataform update(@PathVariable Long id, @RequestBody UserPlataform user) {
        Optional<UserPlataform> userOptional = userService.findById(id);

        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, messageService.get("user.not-found"));
        }

        user.setId(id);
        return userService.save(user);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        Optional<UserPlataform> userOptional = userService.findById(id);

        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, messageService.get("user.not-found"));
        }

        userService.deleteById(id);
    }

    @PostMapping(path = "/order/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody Order order) {
        try {
            userService.createOrder(order);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, messageService.get("order.error-process"));
        }
    }

    @GetMapping(path = "/order/{id}")
    @CircuitBreaker(name = "findOrderCB", fallbackMethod = "findOrderFallBack")
    public Order findOrderById(@PathVariable Long id) {
        return userService.findOrderById(id);
    }

    public Order findOrderFallBack(Throwable e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, messageService.get("service.off"));
    }
}
