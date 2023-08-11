package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.security.model.RegisterUserDto;
import com.techelevator.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers(); // /user

    User getUserById(int id); // /user/{id}

   User getUserByUsername(String username); // /user/{username}

    User createUser(RegisterUserDto user); // /user POST
    User createChildUser(RegisterUserDto childUser); // /user POST

    boolean deactivateFamilyMember(int id); // /user/{id}

    boolean deactivateFamily(int id); // user/family/{id}
    User getUserByFamilyId(int id);
}
