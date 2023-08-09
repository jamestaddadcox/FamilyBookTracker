package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userDao.getUsers();

    }
    @RequestMapping(path = "/user/{username}", method = RequestMethod.GET)
    public User getUserByUsername(@PathVariable String username) {
        return userDao.getUserByUsername(username);
    }


}







