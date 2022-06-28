package com.eeat.restaurantservice.repository;

import com.eeat.restaurantservice.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByCnpj(Integer cnpj);
}
