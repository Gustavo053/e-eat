package com.eeat.userservice.controller;

import com.eeat.userservice.model.Address;
import com.eeat.userservice.service.AddressService;
import com.eeat.userservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/address")
public class AddresController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<Address> findAll() {
        return addressService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Address findById(@PathVariable Long id) {
        Optional<Address> addressOptional = addressService.findById(id);

        if (!addressOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, messageService.get("address.not-found"));
        }

        return addressOptional.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address save(@RequestBody Address address) {
        return addressService.save(address);
    }

    @PutMapping(path = "/{id}")
    public Address update(@PathVariable Long id, @RequestBody Address address) {
        Optional<Address> addressOptional = addressService.findById(id);

        if (!addressOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, messageService.get("address.not-found"));
        }

        address.setId(id);
        return addressService.save(address);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        Optional<Address> addressOptional = addressService.findById(id);

        if (!addressOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, messageService.get("address.not-found"));
        }

        addressService.deleteById(id);
    }
}
