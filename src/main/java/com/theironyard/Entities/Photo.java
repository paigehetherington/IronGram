package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by vajrayogini on 3/15/16.
 */
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    User sender;

    @ManyToOne
    User recicipient;

    @Column(nullable = false)
    String fileName;

    public Photo(User sender, User recicipient, String fileName) {
        this.sender = sender;
        this.recicipient = recicipient;
        this.fileName = fileName;
    }

    public Photo() {
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecicipient() {
        return recicipient;
    }

    public void setRecicipient(User recicipient) {
        this.recicipient = recicipient;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
