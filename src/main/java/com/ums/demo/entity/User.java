package com.ums.demo.entity;

import jakarta.persistence.*;

@Entity  //Marks a Java class as a JPA entity. This tells the JPA provider (e.g., Hibernate) that instances of this class can be stored in and retrieved from a database.
@Table(name="users") // we can specify the name of the table here
public class User {

    @Id // maps the id field as primary key
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // used to generate the strategy type like ids should be stored as sequence or identity etc
    private Long id;
    private String firstName;
    private String email;

    public User(Long id, String firstName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
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
