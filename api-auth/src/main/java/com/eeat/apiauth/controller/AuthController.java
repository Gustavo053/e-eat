package com.eeat.apiauth.controller;

import com.eeat.apiauth.DTO.LoginDTO;
import com.eeat.apiauth.model.UserCredentials;
import com.eeat.apiauth.repository.UserCredentialsRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @PostMapping
    public LoginDTO authentication(@RequestBody LoginDTO loginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getPassword())
        );

        loginDTO.setToken(createToken(loginDTO.getLogin()));
        loginDTO.setPassword(null);
        return loginDTO;
    }

    @PostMapping(path = "/create-credentials")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createUserCredentials(@RequestBody UserCredentials userCredentials) {
        UserCredentials userCredentialsSaved = userCredentialsRepository.findByLogin(userCredentials.getLogin());

        if (userCredentialsSaved != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login already exits");
        }

        userCredentials.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
        return ResponseEntity.ok(userCredentialsRepository.save(userCredentials));
    }

    private String createToken(String subject) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, "javatechie").compact();
    }
}
