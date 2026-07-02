package com.example.hospitalms.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    private int telephone;
    private String address;
}
