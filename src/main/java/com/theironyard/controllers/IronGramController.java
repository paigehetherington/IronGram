package com.theironyard.controllers;

import com.sun.deploy.net.HttpResponse;
import com.theironyard.entities.Photo;
import com.theironyard.entities.User;
import com.theironyard.services.PhotoRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utils.PasswordStorage;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vajrayogini on 3/15/16.
 */
@RestController //get routes return JSON, not strings but objects
public class IronGramController {

    @Autowired
    UserRepository users;


    @Autowired
    PhotoRepository photos;

    Server dbui = null;

    @PostConstruct
    public void init() throws SQLException {
       dbui = Server.createWebServer().start();
        }

    @PreDestroy
    public void destroy() {
        dbui.stop();
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login(String username, String password, HttpSession session, HttpServletResponse response) throws Exception {
        User user = users.findByName(username);
        if (user == null) {
            user = new User(username, PasswordStorage.createHash(password));
            users.save(user);
        }
        else if (!PasswordStorage.verifyPassword(password, user.getPasswordHash())) {
            throw new Exception("Wrong password");
        }
        session.setAttribute("username", username);
        response.sendRedirect("/");
        return user;
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public User getUser(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return users.findByName(username);
    }

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public Photo upload(MultipartFile photo, HttpSession session, HttpServletResponse response, String recipient, int photoLife) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("Not logged in.");
        }
        User userS = users.findByName(username);
        File photoFile = File.createTempFile("image", photo.getOriginalFilename(), new File("public"));
        FileOutputStream fos = new FileOutputStream(photoFile);
        fos.write(photo.getBytes());
        User userR = users.findByName(recipient);


        Photo p = new Photo(userS, userR, photoFile.getName(), photoLife);


        photos.save(p);

        response.sendRedirect("/");
        return p;
    }

    @RequestMapping(path = "/photos", method = RequestMethod.GET)
    public List<Photo> photos() {
        List<Photo> photosList = (ArrayList<Photo>) photos.findAll();

        for  (Photo p : photosList) {
            if (p.getDateTime() == null) {
                p.setDateTime(LocalDateTime.now());
                photos.save(p);
            }
            else if (LocalDateTime.now().isAfter(p.getDateTime().plusSeconds(p.getPhotoLife()))) {
                photos.delete(p); //to delete from db
                File file = new File ("/public", p.getFileName());
                file.delete(); //to delete from disk (public file)



            }
        }
        return (List<Photo>) photos.findAll();
    }

}

