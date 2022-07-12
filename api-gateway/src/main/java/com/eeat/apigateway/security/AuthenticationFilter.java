package com.eeat.apigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Component
public class AuthenticationFilter implements GlobalFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request =  exchange.getRequest();
        URI uri = request.getURI();

        if (!uri.getPath().equals("/auth") && !uri.getPath().equals("/user")) {
            HttpHeaders httpHeaders = request.getHeaders();

            List<String> headerAuth = httpHeaders.get("Authorization");

            if (headerAuth == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token");
            }

            String authorizationHeader = headerAuth.get(0);
            String token;
            String username;

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
                token = authorizationHeader.substring(7);
                username = jwtUtil.extractUsername(token);

                if (username == null || username.trim().equals("")) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid token");
            }
        }

        return chain.filter(exchange);
    }
}
