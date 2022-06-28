package com.eeat.userservice.repository;

import com.eeat.userservice.model.UserPlataform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserPlataform, Long> {
}
