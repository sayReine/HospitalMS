package com.example.hospitalms.repository;

import com.example.hospitalms.model.User;
//import com.example.springbootsecurity.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
