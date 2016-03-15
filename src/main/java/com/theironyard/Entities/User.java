package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by vajrayogini on 3/15/16.
 */
@Entity
@Table(name="users")

public class User {
    public User() {
    }

    @Id

    @GeneratedValue
    int id;

    @Column(nullable = false, unique = true)
    String name;

    @Column(nullable = false)
    String passwordHash;

    public User(String name, String passwordHash) {
        this.name = name;
        this.passwordHash = passwordHash;
    }

    public String getName() {
        return name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasswordHash(String paswordHash) {
        this.passwordHash = paswordHash;
    }
}
