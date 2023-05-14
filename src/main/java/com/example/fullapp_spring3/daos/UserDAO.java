package com.example.fullapp_spring3.daos;

import com.example.fullapp_spring3.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    User findUserByUsername(String username);
}
