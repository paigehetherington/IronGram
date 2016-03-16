package com.theironyard.entities;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    User recipient;

    @Column(nullable = false)
    String fileName;

    LocalDateTime dateTime;

    @Column(nullable = false)
    int photoLife;

    public Photo(User sender, User recipient, String fileName, int photoLife) {
        this.sender = sender;
        this.recipient = recipient;
        this.fileName = fileName;
        this.photoLife = photoLife;
        //

    }

    public Photo() {
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getPhotoLife() {
        return photoLife;
    }

    public void setPhotoLife(int photoLife) {
        this.photoLife = photoLife;
    }
}
