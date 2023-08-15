package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.security.model.RegisterUserDto;
import com.techelevator.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserDao {

    List<User> getUsers(); // /user

    User getUserById(int id); // /user/{id}

   User getUserByUsername(String username); // /user/{username}

    User createUser(RegisterUserDto user, int familyId); // /user POST
    User createChildUser(RegisterUserDto childUser, int familyId); // /user POST

    boolean deactivateFamilyMember(int id); // /user/{id}

   // boolean updateAvatar(int id, MultipartFile image); // /user/{id}/avatar

    boolean deactivateFamily(int id); // user/family/{id}
    User getUserByFamilyId(int id);
}
