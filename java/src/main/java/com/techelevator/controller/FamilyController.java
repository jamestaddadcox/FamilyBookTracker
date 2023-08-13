package com.techelevator.controller;

import com.techelevator.dao.FamilyDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Family;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController

public class FamilyController {
    private final FamilyDao familyDao;
    private final UserDao userDao;

@Autowired
    public FamilyController(FamilyDao familyDao, UserDao userDao) {
        this.familyDao = familyDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/family/{id}", method = RequestMethod.GET)
    public Family getFamilyById(@PathVariable Integer id) {
    if (id == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID is required >:(");
    }
        Family family = familyDao.getFamilyById(id);
        if (family == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found :(");
        } else {
            return family;
        }
}

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/family", method = RequestMethod.POST)
    public ResponseEntity<Family> createFamily(@RequestBody Family family) {
        User user = new User();
        if (family == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Family Not Found :(");
        } else {
            Family createdFamily = familyDao.createFamily(family.getFamilyName());
            return new ResponseEntity<>(createdFamily, HttpStatus.CREATED);
        }
}
    @RequestMapping(path = "/family/{id}/user", method = RequestMethod.GET)
    public List<User> getFamilyMembersByFamilyId(@PathVariable int id) {
        return familyDao.getFamilyMembersByFamilyId(id);
}




}
