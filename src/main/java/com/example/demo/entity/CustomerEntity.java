package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @Column(name = "customer_name")
    private String name;
    @Column(name = "customer_email")
    private String email;

    @OneToMany(mappedBy = "customerEntity")
    @JsonIgnore
    private List<CarEntity> carEntities;

}
