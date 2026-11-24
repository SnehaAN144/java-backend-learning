package com.ecomapp.app;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok: Generates getters, setters, toString(), equals() and hashCode()
@NoArgsConstructor // Generates default (no-argument) constructor required by JPA
@AllArgsConstructor // Generates constructor with all fields (optional for JPA)

@Entity // Marks this class as a JPA entity to be mapped to a database table
@Table(name = "user_table") // Specifies custom database table name

public class User {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates unique ID values

    private Long id;

    private String firstName; // Maps to a database column
    private String lastName;  // Maps to a database column
}