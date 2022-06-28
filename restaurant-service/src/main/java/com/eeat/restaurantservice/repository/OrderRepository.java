package com.eeat.restaurantservice.repository;

import com.eeat.restaurantservice.model.OrderUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderUser, Long> {
}
