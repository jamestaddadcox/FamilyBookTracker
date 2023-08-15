package com.techelevator.controller;

import com.techelevator.dao.FamilyDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Family;
import com.techelevator.model.User;
import com.techelevator.security.model.RegisterUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class UserController {

    private final UserDao userDao;
    private final FamilyDao familyDao;

    @Autowired
    public UserController(UserDao userDao, FamilyDao familyDao) {
        this.userDao = userDao;
        this.familyDao = familyDao;
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable int id) {
        return userDao.getUserById(id);
    }
    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userDao.getUsers();

    }
    /*@RequestMapping(path = "/user/{username}", method = RequestMethod.GET)
    public User getUserByUsername(@PathVariable String username) {
        return userDao.getUserByUsername(username);
    }*/

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody RegisterUserDto user, int familyId) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found :(");
        } else {

            User createdUser = userDao.createUser(user, familyId);

            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/child-user", method = RequestMethod.POST)
    public ResponseEntity<User> createChildUser(@RequestBody RegisterUserDto childUser, int familyId) {
        if (childUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found :(");
        } else {
            User createdChildUser = userDao.createChildUser(childUser, familyId);
            return new ResponseEntity<>(createdChildUser, HttpStatus.CREATED);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> deactivateFamilyMember(@PathVariable int id) {
        boolean deactivated = userDao.deactivateFamilyMember(id);

        if (deactivated) {
            return ResponseEntity.ok("Family member deactivated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Family member not found.");
        }
    }
//////WIP
//    @RequestMapping(path = "/user/{id}/avatar", method = RequestMethod.PUT)
//    public ResponseEntity<String> updateAvatar(@PathVariable int id, @RequestParam("file") MultipartFile file) {
//        User user = userDao.getUserById(id);
//        String originalAvatar = user.getAvatarUrl();
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//            userDao.updateAvatar(user.getUserId(), file);////this has been wrapped to be a string.
//            if (!user.getAvatarUrl().equals(originalAvatar)){
//                return ResponseEntity.ok("Avatar updated successfully");}
//            else {return ResponseEntity.badRequest().body("Avatar not updated");}
//
//    }

    @RequestMapping(path = "/user/family/{id}", method = RequestMethod.PUT)
    public boolean deactivatedFamily(@PathVariable int id) {
        boolean deactivated = userDao.deactivateFamily(id);

        if (deactivated) {
            return ResponseEntity.ok("Family deactivated successfully.").hasBody();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Family not found.").hasBody();
        }
    }
    @RequestMapping(path = "/user/family/{id}", method = RequestMethod.GET)
    public User getUserByFamilyId(@PathVariable int id) {
        return userDao.getUserByFamilyId(id);
    }




}







