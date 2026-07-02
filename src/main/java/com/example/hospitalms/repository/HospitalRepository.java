package com.example.hospitalms.repository;

import com.example.hospitalms.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    boolean existsByName(String name);
}