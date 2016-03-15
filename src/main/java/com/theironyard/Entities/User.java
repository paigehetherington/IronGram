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
    String paswordHash;

    public User(String name, String paswordHash) {
        this.name = name;
        this.paswordHash = paswordHash;
    }

    public String getName() {
        return name;
    }

    public String getPaswordHash() {
        return paswordHash;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPaswordHash(String paswordHash) {
        this.paswordHash = paswordHash;
    }
}
