package com.eeat.userservice.controller;

import com.eeat.userservice.model.User;
import com.eeat.userservice.service.MessageService;
import com.eeat.userservice.service.UserService;
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
    private MessageService messageService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(path = "/{id}")
    public User findById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);

        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, messageService.get("user.not-found"));
        }

        return userOptional.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(User user) {
        return userService.save(user);
    }

    @PutMapping(path = "/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = userService.findById(id);

        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, messageService.get("user.not-found"));
        }

        user.setId(id);
        return userService.save(user);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        Optional<User> userOptional = userService.findById(id);

        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, messageService.get("user.not-found"));
        }

        userService.deleteById(id);
    }
}
