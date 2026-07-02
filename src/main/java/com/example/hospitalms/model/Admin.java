package com.example.hospitalms.model;

import jakarta.persistence.*;


@Entity

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
  private String full_Name;
  @Column(name = "email",unique = true,nullable = false)
 private String email;
 private String password;
 private String phone_Number;

}
