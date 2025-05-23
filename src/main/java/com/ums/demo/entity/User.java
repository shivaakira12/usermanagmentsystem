package com.ums.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity // Marks a Java class as a JPA entity. This tells the JPA provider (e.g.,
        // Hibernate) that instances of this class can be stored in and retrieved from a
        // database.
@Table(name = "users") // we can specify the name of the table here
public class User {

    @Id // maps the id field as primary key
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // used to generate the strategy type like ids should be stored
                                                        // as sequence or identity etc
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Column(name = "username")
    private String firstName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Column(unique = true)
    private String email;

    public User(Long id, String firstName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
