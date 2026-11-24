package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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
    private String lastName;
    private String email;
    private String phone;
    private UserRole role=UserRole.CUSTOMER;// Maps to a database column

    @OneToOne(cascade=CascadeType.ALL,orphanRemoval=true)
    @JoinColumn(name="address_id",referencedColumnName = "id")
    private Address address;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}