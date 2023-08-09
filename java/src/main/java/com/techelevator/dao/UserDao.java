package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.security.model.RegisterUserDto;
import com.techelevator.model.User;

import java.util.List;

public interface UserDao {
    String getFullNameById(int id);
    boolean addBookToList(Book book); // Post: /user/{id}/list
    List<Book> listBooksByUserId(int id); // /user/{id}/list
    boolean deleteBookFromListByIsbn(String isbn); // /user/{id}/list/{isbn}

    List<User> getUsers(); // /user

    User getUserById(int id); // /user/{id}

    User getUserByUsername(String username); // /user/{username}

    User createUser(RegisterUserDto user); // /user POST
    User createChildUser(RegisterUserDto childUser); // /user POST

    boolean deactivateFamilyMember(User user); // /family/user/

    boolean deactivateFamily(int familyId); // /family/user/{family_id}
    User getUserByFamilyId(int id);
}
