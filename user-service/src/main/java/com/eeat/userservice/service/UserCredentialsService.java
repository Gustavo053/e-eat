package com.eeat.userservice.service;

import com.eeat.userservice.DTO.UserCredentialsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api-auth")
public interface UserCredentialsService {
    @PostMapping(value = "/auth/create-credentials")
    ResponseEntity createUserCredentials(@RequestBody UserCredentialsDTO userCredentialsDTO);
}
